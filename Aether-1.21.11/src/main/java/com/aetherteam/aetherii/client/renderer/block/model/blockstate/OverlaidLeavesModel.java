package com.aetherteam.aetherii.client.renderer.block.model.blockstate;

import com.aetherteam.aetherii.block.natural.AetherLeavesBlock;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OverlaidLeavesModel extends WrapperBlockStateModel {
    public OverlaidLeavesModel(BlockStateModel originalModel) {
        super(originalModel);
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, Predicate<Direction> cullTest) {
        List<BlockModelPart> parts = new ArrayList<>();
        this.wrapped.collectParts(random, parts);
        boolean cutout = Minecraft.getInstance().options.cutoutLeaves().get();
        for (BlockModelPart part : parts) {
            TriState ao = part.useAmbientOcclusion() ? TriState.TRUE : TriState.FALSE;
            for (Direction direction : Direction.values()) {
                if (cullTest.test(direction)) {
                    continue;
                }
                List<BakedQuad> quads = part.getQuads(direction);
                if (direction.getAxis().isHorizontal() && quads.size() >= 3) {
                    BlockState relativeState = level.getBlockState(pos.relative(direction));
                    if (!(relativeState.getBlock() instanceof AetherLeavesBlock)
                            || (relativeState.getValue(AetherLeavesBlock.SNOWY) != state.getValue(AetherLeavesBlock.SNOWY))
                            || (relativeState.getValue(AetherLeavesBlock.MOSSY) != state.getValue(AetherLeavesBlock.MOSSY))) {
                        this.emit(emitter, quads.get(0), direction, ao, cutout, false);
                        this.emit(emitter, quads.get(2), direction, ao, cutout, true);
                    } else {
                        this.emit(emitter, quads.get(1), direction, ao, cutout, false);
                    }
                } else if (direction == Direction.UP && quads.size() >= 2) {
                    this.emit(emitter, quads.get(0), direction, ao, cutout, false);
                    this.emit(emitter, quads.get(1), direction, ao, cutout, true);
                } else {
                    for (BakedQuad quad : quads) {
                        this.emit(emitter, quad, direction, ao, cutout, false);
                    }
                }
            }
            for (BakedQuad quad : part.getQuads(null)) {
                this.emit(emitter, quad, null, ao, cutout, false);
            }
        }
    }

    private void emit(QuadEmitter emitter, BakedQuad quad, Direction cullFace, TriState ao, boolean cutout, boolean forceCutout) {
        emitter.fromBakedQuad(quad).cullFace(cullFace).ambientOcclusion(ao)
                .renderLayer(forceCutout || cutout ? ChunkSectionLayer.CUTOUT : ChunkSectionLayer.SOLID).emit();
    }

    @Override
    public Object createGeometryKey(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random) {
        return null; // Geometry depends on adjacent leaves and the current graphics setting.
    }
}
