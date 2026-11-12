package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.particle.AetherIIParticleTypes;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.core.particles.SimpleParticleType;
@Mixin(WeatherEffectRenderer.class)
public class ClientWeatherMixin {
    @ModifyExpressionValue(method = "tickRainParticles", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;RAIN:Lnet/minecraft/core/particles/SimpleParticleType;"))
    private SimpleParticleType aether_ii$rainSplash(SimpleParticleType original, @Local(argsOnly = true) ClientLevel level) {
        return AetherIIDimensionRenderers.isHolyIsles(level) ? AetherIIParticleTypes.RAIN : original;
    }
    @ModifyExpressionValue(method = "tickRainParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getRainLevel(F)F"))
    private float aether_ii$stormIntensity(float rain, @Local(argsOnly = true) ClientLevel level) {
        return AetherIIDimensionRenderers.isHolyIsles(level) && level.getThunderLevel(1.0F) > 0 ? rain * 2.0F : rain;
    }
}
