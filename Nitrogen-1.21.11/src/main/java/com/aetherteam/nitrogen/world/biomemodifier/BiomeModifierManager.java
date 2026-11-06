package com.aetherteam.nitrogen.world.biomemodifier;

import com.aetherteam.nitrogen.Nitrogen;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.SimpleResourceReloader;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class BiomeModifierManager extends SimpleResourceReloader<Map<Identifier, JsonElement>> {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(Nitrogen.MODID, "biome_modifiers");
    private static List<BiomeModificationData> loaded = List.of();
    private static boolean registered = false;

    @Override
    protected Map<Identifier, JsonElement> prepare(PreparableReloadListener.SharedState state) {
        ResourceManager manager = state.resourceManager();
        Map<Identifier, JsonElement> result = new HashMap<>();

        FileToIdConverter primary = FileToIdConverter.json("biome_modifier");
        for (Map.Entry<Identifier, Resource> entry : primary.listMatchingResources(manager).entrySet()) {
            Identifier location = entry.getKey();
            Identifier id = primary.fileToId(location);
            try (Reader reader = entry.getValue().openAsReader()) {
                result.put(id, JsonParser.parseReader(reader));
            } catch (Exception e) {
                Nitrogen.LOGGER.error("Couldn't parse biome modifier JSON '{}' from '{}'", id, location, e);
            }
        }

        FileToIdConverter legacy = FileToIdConverter.json("neoforge/biome_modifier");
        for (Map.Entry<Identifier, Resource> entry : legacy.listMatchingResources(manager).entrySet()) {
            Identifier location = entry.getKey();
            Identifier id = legacy.fileToId(location);
            if (!result.containsKey(id)) {
                try (Reader reader = entry.getValue().openAsReader()) {
                    result.put(id, JsonParser.parseReader(reader));
                } catch (Exception e) {
                    Nitrogen.LOGGER.error("Couldn't parse legacy biome modifier JSON '{}' from '{}'", id, location, e);
                }
            }
        }

        return result;
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, PreparableReloadListener.SharedState state) {
        HolderLookup.Provider registries = state.get(ResourceLoader.RELOADER_REGISTRY_LOOKUP_KEY);
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
        Codec<BiomeModificationData> codec = NitrogenBiomeModifierSerializers.BIOME_MODIFIER_SERIALIZERS.byNameCodec()
                .dispatch(BiomeModificationData::codec, Function.identity());

        List<BiomeModificationData> list = new ArrayList<>();
        for (Map.Entry<Identifier, JsonElement> entry : prepared.entrySet()) {
            Identifier id = entry.getKey();
            DataResult<BiomeModificationData> parseResult = codec.parse(ops, entry.getValue());
            parseResult.ifSuccess(list::add)
                    .ifError(err -> Nitrogen.LOGGER.error("Failed to decode biome modifier '{}': {}", id, err.message()));
        }

        loaded = List.copyOf(list);
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;

        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(ID, new BiomeModifierManager());
        BiomeModification modification = BiomeModifications.create(Identifier.fromNamespaceAndPath(Nitrogen.MODID, "data_driven"));
        for (ModificationPhase phase : ModificationPhase.values()) {
            modification.add(phase, ctx -> true, (selCtx, modCtx) -> {
                for (BiomeModificationData data : loaded) {
                    if (data.phase() == phase && data.selector().test(selCtx)) {
                        data.modify(selCtx, modCtx);
                    }
                }
            });
        }
    }
}
