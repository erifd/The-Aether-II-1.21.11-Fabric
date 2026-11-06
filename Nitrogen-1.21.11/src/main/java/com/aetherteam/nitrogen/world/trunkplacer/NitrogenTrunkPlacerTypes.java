package com.aetherteam.nitrogen.world.trunkplacer;

import com.aetherteam.nitrogen.Nitrogen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class NitrogenTrunkPlacerTypes {
    public static final TrunkPlacerType<HookedTrunkPlacer> HOOKED_TRUNK_PLACER = Registry.register(
        BuiltInRegistries.TRUNK_PLACER_TYPE,
        Identifier.fromNamespaceAndPath(Nitrogen.MODID, "hooked_trunk_placer"),
        new TrunkPlacerType<>(HookedTrunkPlacer.CODEC)
    );

    public static void init() {
    }
}
