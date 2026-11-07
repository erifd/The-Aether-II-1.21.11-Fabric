package com.aetherteam.aetherii.client.renderer.block.model.blockstate;

import net.fabricmc.fabric.api.util.TriState;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.natural.AetherLeavesBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.block.model.SimpleModelWrapper;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.resources.model.QuadCollection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class OverlaidLeavesModel extends DelegateBlockStateModel {
    public OverlaidLeavesModel(BlockStateModel originalModel) {
        super(originalModel);
    }

    public void collectParts(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> parts) {
        List<BlockStateModelPart> newParts = new ArrayList<>();
        this.wrapped.collectParts(random, newParts);
        for (BlockStateModelPart part : newParts) {
            QuadCollection.Builder builder = new QuadCollection.Builder();
            for (Direction direction : DIRECTIONS) {
                List<BakedQuad> quads = part.getQuads(direction);
                if (direction != null && direction.getAxis().isHorizontal()) {
                    if (quads.size() >= 3) {
                        int baseIndex = 0;
                        int defaultIndex = 1;
                        int overlayIndex = 2;
                        BlockState relativeState = level.getBlockState(pos.relative(direction));
                        if (!(relativeState.getBlock() instanceof AetherLeavesBlock)
                                || (relativeState.getValue(AetherLeavesBlock.SNOWY) != state.getValue(AetherLeavesBlock.SNOWY))
                                || (relativeState.getValue(AetherLeavesBlock.MOSSY) != state.getValue(AetherLeavesBlock.MOSSY))) {
                            builder.addCulledFace(direction, this.convertQuad(quads.get(baseIndex)));
                            builder.addCulledFace(direction, this.convertQuad(quads.get(overlayIndex), true));
                        } else {
                            baseBuilder.addCulledFace(direction, quads.getFirst());
                        }
                    } else {
                        if (direction == null) {
                            baseBuilder.addUnculledFace(quads.get(baseIndex));
                            overlayBuilder.addUnculledFace(quads.get(overlayIndex));
                        } else {
                            BlockState relativeState = level.getBlockState(pos.relative(direction));
                            if (!relativeState.is(state.getBlock())
                                    || (relativeState.getValue(AetherLeavesBlock.SNOWY) != state.getValue(AetherLeavesBlock.SNOWY))
                                    || (relativeState.getValue(AetherLeavesBlock.MOSSY) != state.getValue(AetherLeavesBlock.MOSSY))) {
                                baseBuilder.addCulledFace(direction, quads.get(baseIndex));
                                overlayBuilder.addCulledFace(direction, quads.get(overlayIndex));
                            } else {
                                overlayBuilder.addCulledFace(direction, quads.get(regularIndex));
                            }
                        }
                    }
                }
                QuadCollection baseQuads = baseBuilder.build();
                QuadCollection overlayQuads = overlayBuilder.build();
                if (!baseQuads.getAll().isEmpty()) {
                    parts.add(new SimpleModelWrapper(baseBuilder.build(), simpleModelWrapper.useAmbientOcclusion(), simpleModelWrapper.particleIcon(), Minecraft.getInstance().options.cutoutLeaves().get() ? ChunkSectionLayer.CUTOUT : ChunkSectionLayer.SOLID));
                }
                if (!overlayQuads.getAll().isEmpty()) {
                    parts.add(new SimpleModelWrapper(overlayBuilder.build(), simpleModelWrapper.useAmbientOcclusion(), simpleModelWrapper.particleIcon(), ChunkSectionLayer.CUTOUT));
                }
            }
            QuadCollection collection = builder.build();
            if (!collection.getAll().isEmpty()) {
                parts.add(new SimpleModelWrapper(builder.build(), part.useAmbientOcclusion(), part.particleMaterial()));
            }
        }
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, Predicate<Direction> cullTest) {
        List<BlockStateModelPart> parts = new ArrayList<>();
        this.collectParts(level, pos, state, random, parts);
        for (BlockStateModelPart part : parts) {
            for (Direction face : DIRECTIONS) {
                if (face != null && cullTest.test(face)) continue;
                for (BakedQuad quad : part.getQuads(face)) {
                    emitter.fromBakedQuad(quad).cullFace(face)
                            .ambientOcclusion(part.useAmbientOcclusion() ? TriState.TRUE : TriState.FALSE).emit();
                }
            }
        }
    }

    @Override
    public Object createGeometryKey(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random) {
        return null; // Geometry depends on adjacent leaves and the current graphics setting.
    }

    public BakedQuad convertQuad(BakedQuad oldQuad) {
        return this.convertQuad(oldQuad, false);
    }

    public BakedQuad convertQuad(BakedQuad oldQuad, boolean forceCutout) {
        ChunkSectionLayer layer = Minecraft.getInstance().gameRenderer.gameRenderState().optionsRenderState.cutoutLeaves ? oldQuad.materialInfo().layer() : ChunkSectionLayer.SOLID;
        if (forceCutout) {
            layer = ChunkSectionLayer.CUTOUT;
        }
        return new BakedQuad(
                oldQuad.position0(),
                oldQuad.position1(),
                oldQuad.position2(),
                oldQuad.position3(),
                oldQuad.packedUV0(),
                oldQuad.packedUV1(),
                oldQuad.packedUV2(),
                oldQuad.packedUV3(),
                oldQuad.direction(),
                new BakedQuad.MaterialInfo(oldQuad.materialInfo().sprite(), layer, oldQuad.materialInfo().itemRenderType(), oldQuad.materialInfo().itemGlintRenderType(), oldQuad.materialInfo().itemGlintSpecialRenderType(), oldQuad.materialInfo().tintIndex(), oldQuad.materialInfo().shadeDirectionOverride(), oldQuad.materialInfo().lightEmission())
        );
    }
}