package com.aetherteam.aetherii.client.renderer.block.model.blockstate;

import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.resources.model.SimpleModelWrapper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.QuadCollection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.List;

public class BreakingFixModel extends WrapperBlockStateModel {
    private static final Direction[] DIRECTIONS = Arrays.copyOfRange(Direction.values(), 0, 7);

    public BreakingFixModel(BlockStateModel delegate) {
        super(delegate);
    }

    public void collectBreakingParts(RandomSource random, List<BlockModelPart> parts) {
        List<BlockModelPart> newParts = new ArrayList<>();
        this.wrapped.collectParts(random, newParts);
        for (BlockModelPart modelPart : newParts) {
            if (modelPart instanceof SimpleModelWrapper wrapper) {
                QuadCollection.Builder builder = new QuadCollection.Builder();
                for (Direction side : DIRECTIONS) {
                    List<BakedQuad> quads = wrapper.getQuads(side);
                    for (int i = 0; i < quads.size(); i++) {
                        BakedQuad quad = quads.get(i);
                        if (i == quads.size() - 1) {
                            if (side == null) {
                                builder.addUnculledFace(quad);
                            } else {
                                builder.addCulledFace(side, quad);
                            }
                        }
                    }
                }
                parts.add(new SimpleModelWrapper(builder.build(), wrapper.useAmbientOcclusion(), wrapper.particleMaterial()));
            } else {
                parts.add(modelPart);
            }
        }
    }
}
