package com.aetherteam.aetherii.world.surfacerule;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class AetherIISurfaceRules {
    public static final MapCodec<NoisePalette3DPlacementRule> NOISE_PALETTE_3D = Registry.register(
            BuiltInRegistries.MATERIAL_RULE,
            Identifier.fromNamespaceAndPath(AetherII.MODID, "noise_palette_3d"),
            NoisePalette3DPlacementRule.KEY_CODEC.codec()
    );

    public static void init() {}
}