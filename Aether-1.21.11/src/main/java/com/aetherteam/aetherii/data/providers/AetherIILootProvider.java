package com.aetherteam.aetherii.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Adapts the mod's loot sub-providers (which only need a registry lookup) to Fabric's data generation.
 */
public class AetherIILootProvider extends SimpleFabricLootTableProvider {
    private final String name;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final Function<HolderLookup.Provider, ? extends net.minecraft.data.loot.LootTableSubProvider> factory;

    public AetherIILootProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries, ContextKeySet contextType, String name, Function<HolderLookup.Provider, ? extends net.minecraft.data.loot.LootTableSubProvider> factory) {
        super(output, registries, contextType);
        this.name = name;
        this.registries = registries;
        this.factory = factory;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        this.factory.apply(this.registries.join()).generate(consumer);
    }

    @Override
    public String getName() {
        return "Aether II " + this.name + " Loot Tables";
    }
}
