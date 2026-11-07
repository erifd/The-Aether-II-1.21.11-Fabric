package com.aetherteam.aetherii.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.function.Supplier;

public class BlocksLightLeavesBlock extends AetherLeavesBlock {
    public BlocksLightLeavesBlock(Properties properties, ParticleOptions leavesParticle, Supplier<Block> leavesPile) {
        super(properties, leavesParticle, leavesPile);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state) {
        return false;
    }

    @Override
    public int getLightBlock(BlockState state) {
        return 15;
    }
}