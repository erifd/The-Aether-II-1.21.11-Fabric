package com.aetherteam.nitrogen.world.biomemodifier;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import java.util.function.Predicate;

public interface BiomeModificationData {
    void modify(BiomeSelectionContext selectionContext, BiomeModificationContext modificationContext);

    ModificationPhase phase();

    Predicate<BiomeSelectionContext> selector();

    MapCodec<? extends BiomeModificationData> codec();
}
