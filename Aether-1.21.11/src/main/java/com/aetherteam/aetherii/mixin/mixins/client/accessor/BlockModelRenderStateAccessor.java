package com.aetherteam.aetherii.mixin.mixins.client.accessor;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(BlockModelRenderState.class)
public interface BlockModelRenderStateAccessor {
    @Accessor("modelParts")
    List<BlockModelPart> aether_ii$getModelParts();

    @Accessor("modelParts")
    void aether_ii$setModelParts(List<BlockModelPart> modelParts);
}
