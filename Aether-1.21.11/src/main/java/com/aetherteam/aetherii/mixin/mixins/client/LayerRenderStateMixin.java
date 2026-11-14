package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.mixin.wrappers.client.IrradiatedDataWrapper;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes each item layer carry the "irradiated" flag that {@link CuboidItemModelWrapperMixin} sets.
 * <p>
 * 1.21.11 port: this only provides the interface, the field and the reset. The 26.x version also injected into
 * LayerRenderState#submit to draw the irradiated glint; that hook needs the 1.21.11 submit() descriptor and is
 * added separately once it has been checked against the 1.21.11 sources.
 */
@Mixin(ItemStackRenderState.LayerRenderState.class)
public class LayerRenderStateMixin implements IrradiatedDataWrapper {
    @Unique
    private boolean aether_ii$isIrradiated;

    @Inject(method = "clear", at = @At("HEAD"))
    private void aether_ii$clearIrradiated(CallbackInfo ci) {
        this.aether_ii$isIrradiated = false;
    }

    @Override
    public void aether_ii$setIrradiated(boolean irradiated) {
        this.aether_ii$isIrradiated = irradiated;
    }

    @Override
    public boolean aether_ii$getIrradiated() {
        return this.aether_ii$isIrradiated;
    }
}
