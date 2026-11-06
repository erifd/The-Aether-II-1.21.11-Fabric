package com.aetherteam.nitrogen.client.renderer;

import com.aetherteam.nitrogen.client.renderer.state.BlockStateRenderState;
import com.aetherteam.nitrogen.integration.jei.FakeLevel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;

import java.util.ArrayList;
import java.util.List;

public class BlockStateRenderer extends PictureInPictureRenderer<BlockStateRenderState> {

    public BlockStateRenderer(MultiBufferSource.BufferSource source) {
        super(source);
    }

    @Override
    public Class<BlockStateRenderState> getRenderStateClass() {
        return BlockStateRenderState.class;
    }

    @Override
    protected void renderToTexture(BlockStateRenderState state, PoseStack stack) {
        BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();

        stack.translate(0.425F, -0.25F, 5.0F);
        stack.mulPose(Axis.XP.rotationDegrees(-30.0F));
        stack.mulPose(Axis.YP.rotationDegrees(45.0F));
        stack.scale(-0.6F, -0.6F, -0.6F);

        ModelBlockRenderer modelBlockRenderer = blockRenderDispatcher.getModelRenderer();
        BlockStateModel model = blockRenderDispatcher.getBlockModel(state.blockState());
        BlockAndTintGetter level = FakeLevel.of(state.blockState());
        List<BlockModelPart> parts = new ArrayList<>();
        model.collectParts(RandomSource.create(0L), parts);
        modelBlockRenderer.tesselateBlock(level, parts, state.blockState(), BlockPos.ZERO, stack, this.bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(state.blockState())), false, OverlayTexture.NO_OVERLAY);
        this.bufferSource.endBatch();
    }

    @Override
    protected String getTextureLabel() {
        return "BlockState";
    }
}
