package com.aetherteam.aetherii.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.renderer.RenderPipelines;

/**
 * 1.21.11 port: the custom dither/cloud-cover shaders were written against the 26.x pipeline API and are not ported yet,
 * so the dithered entity layers fall back to the vanilla cutout pipeline.
 */
public class AetherIIRenderPipelines {
    public static void registerShaders() {
    }

    public static RenderPipeline getEntityDitherNoCull() {
        return RenderPipelines.ENTITY_CUTOUT_NO_CULL;
    }
}
