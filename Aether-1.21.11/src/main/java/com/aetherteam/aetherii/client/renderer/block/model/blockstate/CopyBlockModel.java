package com.aetherteam.aetherii.client.renderer.block.model.blockstate;

import java.util.function.Predicate;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import com.aetherteam.aetherii.blockentity.LockedBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyBlockModel extends WrapperBlockStateModel {
    private static final Direction[] DIRECTIONS = Arrays.copyOfRange(Direction.values(), 0, 7);

    public CopyBlockModel(BlockStateModel delegate) {
        super(delegate);
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, Predicate<Direction> cullTest) {
        if (!(level.getBlockEntityRenderData(pos) instanceof LockedBlockEntity.CopyData data)) return;
        BlockState mimicState = data.state();
        BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(mimicState);
        if (model instanceof CopyBlockModel) return;
        emitter.pushTransform(quad -> {
            quad.renderLayer(quad.emissive() || quad.lightmap(0) > 0 ? ChunkSectionLayer.CUTOUT : ChunkSectionLayer.SOLID);
            quad.tintIndex(-1);
            for (int i = 0; i < 4; i++) {
                quad.color(i, ARGB.multiply(quad.color(i), -4276546));
            }
            return true;
        });
        try {
            model.emitQuads(emitter, level, pos, mimicState, random, cullTest);
        } finally {
            emitter.popTransform();
        }
    }

    @Override
    public Object createGeometryKey(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random) {
        return null;
    }

    @Override
    public TextureAtlasSprite particleSprite(BlockAndTintGetter level, BlockPos pos, BlockState state) {
        Object renderData = level.getBlockEntityRenderData(pos);
        if (!(renderData instanceof LockedBlockEntity.CopyData data)) {
            return super.particleSprite(level, pos, state);
        }
        BlockState mimicState = data.state();
        return Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(mimicState).particleSprite(level, pos, mimicState);
    }
}
