package com.aetherteam.aetherii.client.renderer.blockentity;

import com.aetherteam.aetherii.client.AetherIIAtlases;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;

public class SkyrootChestRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
    private final MaterialSet skyrootMaterials;
    private final ChestModel singleModel;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;

    public SkyrootChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.skyrootMaterials = context.materials();
        this.singleModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
        this.doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        this.doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.angle));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        float open = state.open;
        open = 1.0F - open;
        open = 1.0F - open * open * open;
        Material material = switch (state.type) {
            case LEFT -> AetherIIAtlases.SKYROOT_CHEST_LEFT_MATERIAL;
            case RIGHT -> AetherIIAtlases.SKYROOT_CHEST_RIGHT_MATERIAL;
            case SINGLE -> AetherIIAtlases.SKYROOT_CHEST_MATERIAL;
        };
        ChestModel model = switch (state.type) {
            case LEFT -> this.doubleLeftModel;
            case RIGHT -> this.doubleRightModel;
            case SINGLE -> this.singleModel;
        };
        collector.submitModel(model, open, poseStack, material.renderType(model::renderType), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, this.skyrootMaterials.get(material), 0, state.breakProgress);
        poseStack.popPose();
    }
}
