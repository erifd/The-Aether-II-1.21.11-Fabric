package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.world.density.PerlinNoiseFunction;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Seeds every {@link PerlinNoiseFunction} used by a dimension's noise router as soon as its {@link RandomState}
 * (and therefore its world seed) exists. Without this, terrain sampling (e.g. structure height checks) throws
 * "PerlinNoiseFunction has not been initialized yet!" and no chunk can be generated.
 * <p>
 * {@link PerlinNoiseFunction#initialize} mutates the function in place, so visiting the router once is enough: the
 * features that call {@code mapAll} later with the same seed see the already-initialized instances.
 */
@Mixin(RandomState.class)
public class RandomStateMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void aether_ii$initializePerlinNoise(NoiseGeneratorSettings settings, HolderGetter<NormalNoise.NoiseParameters> noises, long seed, CallbackInfo ci) {
        settings.noiseRouter().mapAll(PerlinNoiseFunction.createOrGetVisitor(seed));
    }
}
