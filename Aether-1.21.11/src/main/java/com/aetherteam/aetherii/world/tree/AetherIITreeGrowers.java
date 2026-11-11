package com.aetherteam.aetherii.world.tree;

import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Map;
import java.util.Optional;

public class AetherIITreeGrowers {
    public static final TreeGrower SKYROOT = new TreeGrower("skyroot", 0.2F, Optional.empty(), Optional.empty(), Optional.of(HolyIslesConfiguredFeatures.SHORT_SKYROOT), Optional.of(HolyIslesConfiguredFeatures.LARGE_SKYROOT), Optional.empty(), Optional.empty());
    public static final TreeGrower IRRADIATED_SKYROOT = new TreeGrower("irradiated_skyroot", 0.2F, Optional.empty(), Optional.empty(), Optional.of(HolyIslesConfiguredFeatures.SKYROOT_IRRADIATED), Optional.of(HolyIslesConfiguredFeatures.LARGE_SKYROOT_IRRADIATED), Optional.empty(), Optional.empty());
    public static final TreeGrower SKYPLANE = tree("skyplane", HolyIslesConfiguredFeatures.SKYPLANE);
    public static final TreeGrower IRRADIATED_SKYPLANE = tree("irradiated_skyplane", HolyIslesConfiguredFeatures.SKYPLANE_IRRADIATED);
    public static final TreeGrower SKYBIRCH = tree("skybirch", HolyIslesConfiguredFeatures.SKYBIRCH);
    public static final TreeGrower IRRADIATED_SKYBIRCH = tree("irradiated_skybirch", HolyIslesConfiguredFeatures.SKYBIRCH_IRRADIATED);
    public static final TreeGrower SKYPINE = tree("skypine", HolyIslesConfiguredFeatures.SKYPINE);
    public static final TreeGrower IRRADIATED_SKYPINE = tree("irradiated_skypine", HolyIslesConfiguredFeatures.SKYPINE_IRRADIATED);
    public static final TreeGrower WISPROOT = tree("wisproot", HolyIslesConfiguredFeatures.WISPROOT);
    public static final TreeGrower IRRADIATED_WISPROOT = tree("irradiated_wisproot", HolyIslesConfiguredFeatures.WISPROOT_IRRADIATED);
    public static final TreeGrower WISPTOP = tree("wisptop", HolyIslesConfiguredFeatures.WISPTOP);
    public static final TreeGrower IRRADIATED_WISPTOP = tree("irradiated_wisptop", HolyIslesConfiguredFeatures.WISPTOP_IRRADIATED);
    public static final TreeGrower GREATROOT = megaTree("greatroot", HolyIslesConfiguredFeatures.GREATROOT);
    public static final TreeGrower IRRADIATED_GREATROOT = megaTree("irradiated_greatroot", HolyIslesConfiguredFeatures.GREATROOT_IRRADIATED);
    public static final TreeGrower GREATOAK = megaTree("greatoak", HolyIslesConfiguredFeatures.GREATOAK);
    public static final TreeGrower IRRADIATED_GREATOAK = megaTree("irradiated_greatoak", HolyIslesConfiguredFeatures.GREATOAK_IRRADIATED);
    public static final TreeGrower GREATBOA = megaTree("greatboa", HolyIslesConfiguredFeatures.GREATBOA);
    public static final TreeGrower IRRADIATED_GREATBOA = megaTree("irradiated_greatboa", HolyIslesConfiguredFeatures.GREATBOA_IRRADIATED);
    public static final TreeGrower AMBEROOT = tree("amberoot", HolyIslesConfiguredFeatures.TREES_AMBEROOT_SPARSE);

    public static final Map<TreeGrower, TreeGrower> NORMAL_TO_IRRADIATED = Map.ofEntries(
            Map.entry(AetherIITreeGrowers.SKYROOT, AetherIITreeGrowers.IRRADIATED_SKYROOT),
            Map.entry(AetherIITreeGrowers.SKYPLANE, AetherIITreeGrowers.IRRADIATED_SKYPLANE),
            Map.entry(AetherIITreeGrowers.SKYBIRCH, AetherIITreeGrowers.IRRADIATED_SKYBIRCH),
            Map.entry(AetherIITreeGrowers.SKYPINE, AetherIITreeGrowers.IRRADIATED_SKYPINE),
            Map.entry(AetherIITreeGrowers.WISPROOT, AetherIITreeGrowers.IRRADIATED_WISPROOT),
            Map.entry(AetherIITreeGrowers.WISPTOP, AetherIITreeGrowers.IRRADIATED_WISPTOP),
            Map.entry(AetherIITreeGrowers.GREATROOT, AetherIITreeGrowers.IRRADIATED_GREATROOT),
            Map.entry(AetherIITreeGrowers.GREATOAK, AetherIITreeGrowers.IRRADIATED_GREATOAK),
            Map.entry(AetherIITreeGrowers.GREATBOA, AetherIITreeGrowers.IRRADIATED_GREATBOA)
    );

    private static TreeGrower tree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return new TreeGrower(name, Optional.empty(), Optional.of(tree), Optional.empty());
    }

    private static TreeGrower megaTree(String name, ResourceKey<ConfiguredFeature<?, ?>> megaTree) {
        return new TreeGrower(name, Optional.of(megaTree), Optional.empty(), Optional.empty());
    }
}
