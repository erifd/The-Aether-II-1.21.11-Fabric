package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.dungeon.SentryTrapBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {
//    @ModifyArgs(method = "putQuadData(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;Lnet/minecraft/client/renderer/block/ModelBlockRenderer$CommonRenderStorage;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;putBulkData(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;[FFFFF[IIZ)V"))
//    private void putQuadData(Args args, @Local(argsOnly = true) BlockState state) {
//        if (state.is(AetherIIBlocks.LOCKED_BLOCK) || state.is(AetherIIBlocks.BOSS_DOORWAY_BLOCK) || state.is(AetherIIBlocks.TREASURE_DOORWAY_BLOCK) || (state.is(AetherIIBlocks.SENTRY_TRAP) && state.getValue(SentryTrapBlock.LOCKED))) {
//            float red = args.get(3);
//            float green = args.get(4);
//            float blue = args.get(5);
//            args.set(3, red * 0.75F);
//            args.set(4, green * 0.75F);
//            args.set(5, blue * 0.75F);
//        }
//    } //todo

    // 1.21.11: world blocks are drawn by putQuadData(...), which has the BlockState available.
    @WrapOperation(method = "putQuadData(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;Lnet/minecraft/client/renderer/block/ModelBlockRenderer$CommonRenderStorage;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;putBulkData(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;[FFFFF[II)V"))
    private void aether_ii$darkenLockedBlocks(VertexConsumer consumer, PoseStack.Pose pose, BakedQuad quad, float[] brightness, float red, float green, float blue, float alpha, int[] lightmap, int packedOverlay, Operation<Void> original, @Local(argsOnly = true) BlockState state) {
        if (state.is(AetherIIBlocks.LOCKED_BLOCK) || state.is(AetherIIBlocks.BOSS_DOORWAY_BLOCK) || state.is(AetherIIBlocks.TREASURE_DOORWAY_BLOCK) || (state.is(AetherIIBlocks.SENTRY_TRAP) && state.getValue(SentryTrapBlock.LOCKED))) {
            original.call(consumer, pose, quad, brightness, red * 0.75F, green * 0.75F, blue * 0.75F, alpha, lightmap, packedOverlay);
        } else {
            original.call(consumer, pose, quad, brightness, red, green, blue, alpha, lightmap, packedOverlay);
        }
    }
}
