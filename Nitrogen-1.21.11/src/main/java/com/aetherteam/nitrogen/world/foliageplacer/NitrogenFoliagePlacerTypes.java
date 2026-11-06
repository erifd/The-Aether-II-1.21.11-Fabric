package com.aetherteam.nitrogen.world.foliageplacer;

import com.aetherteam.nitrogen.Nitrogen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class NitrogenFoliagePlacerTypes {
    public static final FoliagePlacerType<AetherPineFoliagePlacer> AETHER_PINE_FOLIAGE_PLACER = Registry.register(
        BuiltInRegistries.FOLIAGE_PLACER_TYPE,
        Identifier.fromNamespaceAndPath(Nitrogen.MODID, "aether_pine_foliage_placer"),
        new FoliagePlacerType<>(AetherPineFoliagePlacer.CODEC)
    );
    public static final FoliagePlacerType<HookedFoliagePlacer> HOOKED_FOLIAGE_PLACER = Registry.register(
        BuiltInRegistries.FOLIAGE_PLACER_TYPE,
        Identifier.fromNamespaceAndPath(Nitrogen.MODID, "hooked_foliage_placer"),
        new FoliagePlacerType<>(HookedFoliagePlacer.CODEC)
    );

    public static void init() {
    }
}
