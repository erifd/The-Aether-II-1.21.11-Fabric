package com.aetherteam.aetherii.world.feature;

import com.aetherteam.aetherii.world.feature.configuration.MergedConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MergedFeature extends Feature<MergedConfiguration> {
    public MergedFeature(Codec<MergedConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MergedConfiguration> context) {
        boolean flag = false;
        for (Holder<PlacedFeature> feature : context.config().features()) {
            if (feature.value().place(context.level(), context.chunkGenerator(), context.random(), context.origin())) {
                flag = true;
            }
        }
        return flag;
    }
}
