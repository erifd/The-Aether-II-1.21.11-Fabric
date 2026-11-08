package com.aetherteam.aetherii.block.natural;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlocksLightLeavesBlock extends AetherLeavesBlock {
    public BlocksLightLeavesBlock(Properties properties, ParticleOptions leavesParticle, java.util.function.Supplier<? extends Block> leavesPile) {
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