package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;

public record AlkahestPoolConfiguration(IntProvider count, IntProvider radius, IntProvider offset) {
    public static final MapCodec<AlkahestPoolConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            IntProvider.CODEC.fieldOf("count").forGetter(AlkahestPoolConfiguration::count),
            IntProvider.CODEC.fieldOf("radius").forGetter(AlkahestPoolConfiguration::radius),
            IntProvider.CODEC.fieldOf("offset").forGetter(AlkahestPoolConfiguration::offset)
    ).apply(instance, AlkahestPoolConfiguration::new));
}
