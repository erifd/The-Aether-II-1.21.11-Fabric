package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record AetherLakeConfiguration(IntProvider shrinkScale, BlockStateProvider fluid, BlockStateProvider top) {
    public static final MapCodec<AetherLakeConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            IntProvider.CODEC.fieldOf("shrink_scale").forGetter(AetherLakeConfiguration::shrinkScale),
            BlockStateProvider.CODEC.fieldOf("fluid").forGetter(AetherLakeConfiguration::fluid),
            BlockStateProvider.CODEC.fieldOf("top").forGetter(AetherLakeConfiguration::top)
    ).apply(instance, AetherLakeConfiguration::new));
}