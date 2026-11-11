package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.client.event.listeners.DimensionClientListener;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.nio.ByteBuffer;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @WrapOperation(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/fog/FogRenderer;updateBuffer(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"))
    private void aether_ii$fog(ByteBuffer buffer, int offset, Vector4f color, float environmentalStart, float environmentalEnd, float renderDistanceStart, float renderDistanceEnd, float skyEnd, float cloudEnd, Operation<Void> original, @Local(argsOnly = true) Camera camera, @Local(argsOnly = true, ordinal = 0) int renderDistance, @Local(argsOnly = true) DeltaTracker delta, @Local(argsOnly = true) ClientLevel level) {
        FogData fog = new FogData();
        fog.environmentalStart = environmentalStart;
        fog.environmentalEnd = environmentalEnd;
        Vector4f newColor = new Vector4f(color);
        var fluid = level.getFluidState(camera.blockPosition());
        if (fluid.getType().isSame(AetherIIFluids.ALKAHEST) && camera.position().y < camera.blockPosition().getY() + fluid.getHeight(level, camera.blockPosition())) {
            newColor.set(170 / 255.0F, 226 / 255.0F, 149 / 255.0F, 1.0F);
            fog.environmentalStart = 0;
            fog.environmentalEnd = 12;
        } else {
            DimensionClientListener.adjustFog(camera, fog);
            DimensionClientListener.adjustFogColor(camera, level, renderDistance, delta.getGameTimeDeltaPartialTick(false), newColor);
        }
        original.call(buffer, offset, newColor, fog.environmentalStart, fog.environmentalEnd, renderDistanceStart, renderDistanceEnd, skyEnd, cloudEnd);
    }
}
