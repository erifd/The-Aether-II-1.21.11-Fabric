package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.mixin.wrappers.client.IrradiatedDataWrapper;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockModelWrapper.class)
public class CuboidItemModelWrapperMixin {
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState;setExtents(Ljava/util/function/Supplier;)V"))
    public void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed, CallbackInfo ci, @Local ItemStackRenderState.LayerRenderState layerRenderState) {
        // LayerRenderState implements IrradiatedDataWrapper through LayerRenderStateMixin; never hard-cast it.
        if (!((Object) layerRenderState instanceof IrradiatedDataWrapper wrapper)) {
            return;
        }
        if (!item.hasFoil() && item.is(AetherIITags.Items.IRRADIATED_ITEM)) {
            wrapper.aether_ii$setIrradiated(true);
            output.setAnimated();
        } else {
            wrapper.aether_ii$setIrradiated(false);
        }
    }
}
