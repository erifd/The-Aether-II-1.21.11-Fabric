package com.aetherteam.nitrogen.loot.modifiers;

import com.aetherteam.nitrogen.Nitrogen;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
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
import net.minecraft.world.item.ItemStack;

import java.io.Reader;
import java.util.*;
import java.util.function.Function;

public final class LootModifierManager extends SimpleResourceReloader<Map<Identifier, JsonElement>> {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(Nitrogen.MODID, "loot_modifiers");
    private static List<IGlobalLootModifier> modifiers = List.of();
    private static boolean registered = false;

    @Override
    protected Map<Identifier, JsonElement> prepare(PreparableReloadListener.SharedState state) {
        ResourceManager manager = state.resourceManager();
        FileToIdConverter lister = FileToIdConverter.json("loot_modifiers");
        Map<Identifier, JsonElement> result = new HashMap<>();

        for (Map.Entry<Identifier, Resource> entry : lister.listMatchingResources(manager).entrySet()) {
            Identifier location = entry.getKey();
            Identifier id = lister.fileToId(location);
            try (Reader reader = entry.getValue().openAsReader()) {
                result.put(id, JsonParser.parseReader(reader));
            } catch (Exception e) {
                Nitrogen.LOGGER.error("Couldn't parse loot modifier JSON '{}' from '{}'", id, location, e);
            }
        }
        return result;
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, PreparableReloadListener.SharedState state) {
        HolderLookup.Provider registries = state.get(ResourceLoader.RELOADER_REGISTRY_LOOKUP_KEY);
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
        Codec<IGlobalLootModifier> codec = NitrogenLootModifiers.GLOBAL_LOOT_MODIFIERS.byNameCodec()
                .dispatch(IGlobalLootModifier::codec, Function.identity());

        Set<Identifier> indexEntries = null;
        for (Map.Entry<Identifier, JsonElement> entry : prepared.entrySet()) {
            if (entry.getKey().getPath().equals("global_loot_modifiers")) {
                if (entry.getValue().isJsonObject()) {
                    JsonObject obj = entry.getValue().getAsJsonObject();
                    if (obj.has("entries") && obj.get("entries").isJsonArray()) {
                        indexEntries = new HashSet<>();
                        JsonArray arr = obj.getAsJsonArray("entries");
                        for (JsonElement el : arr) {
                            if (el.isJsonPrimitive()) {
                                Identifier id = Identifier.tryParse(el.getAsString());
                                if (id != null) {
                                    indexEntries.add(id);
                                }
                            }
                        }
                    }
                }
            }
        }

        record ModifierEntry(Identifier id, int priority, IGlobalLootModifier modifier) {}
        List<ModifierEntry> loadedEntries = new ArrayList<>();

        for (Map.Entry<Identifier, JsonElement> entry : prepared.entrySet()) {
            Identifier id = entry.getKey();
            if (id.getPath().equals("global_loot_modifiers")) {
                continue;
            }
            if (indexEntries != null && !indexEntries.contains(id)) {
                continue;
            }

            DataResult<IGlobalLootModifier> parseResult = codec.parse(ops, entry.getValue());
            parseResult.ifSuccess(mod -> {
                int priority = (mod instanceof LootModifier lm) ? lm.priority : IGlobalLootModifier.DEFAULT_PRIORITY;
                loadedEntries.add(new ModifierEntry(id, priority, mod));
            }).ifError(err -> Nitrogen.LOGGER.error("Failed to decode loot modifier '{}': {}", id, err.message()));
        }

        loadedEntries.sort(Comparator.comparingInt(ModifierEntry::priority).thenComparing(ModifierEntry::id));
        modifiers = loadedEntries.stream().map(ModifierEntry::modifier).toList();
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;

        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(ID, new LootModifierManager());
        LootTableEvents.MODIFY_DROPS.register((lootTable, context, drops) -> {
            ObjectArrayList<ItemStack> list = new ObjectArrayList<>(drops);
            for (IGlobalLootModifier modifier : modifiers) {
                list = modifier.apply(list, context);
            }
            drops.clear();
            drops.addAll(list);
        });
    }
}
