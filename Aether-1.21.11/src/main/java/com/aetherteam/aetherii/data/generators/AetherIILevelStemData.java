package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.data.resources.registries.AetherIIDimensions;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.dimension.LevelStem;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * Fabric's FabricDynamicRegistryProvider only handles worldgen registries, so the dimension (LevelStem, registry
 * minecraft:dimension) is written by this provider. It encodes the LevelStem built in
 * {@link AetherIIDimensions#bootstrapLevelStem} to data/aether_ii/dimension/aether_holy_isles.json.
 */
public class AetherIILevelStemData implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public AetherIILevelStemData(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "dimension");
        this.registries = registries;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.registries.thenCompose(provider -> {
            LevelStem stem = provider.lookupOrThrow(Registries.LEVEL_STEM).getOrThrow(AetherIIDimensions.AETHER_HOLY_ISLES_LEVEL_STEM).value();
            RegistryOps<JsonElement> ops = provider.createSerializationContext(JsonOps.INSTANCE);
            var result = LevelStem.CODEC.encodeStart(ops, stem);
            if (result.isError()) {
                throw new IllegalStateException("Could not encode dimension " + AetherIIDimensions.AETHER_HOLY_ISLES_LEVEL_STEM.identifier() + ": " + result.error().get().message());
            }
            Path path = this.pathProvider.json(AetherIIDimensions.AETHER_HOLY_ISLES_LEVEL_STEM.identifier());
            return DataProvider.saveStable(cache, result.result().get(), path);
        });
    }

    @Override
    public String getName() {
        return "Aether II Dimensions";
    }
}
