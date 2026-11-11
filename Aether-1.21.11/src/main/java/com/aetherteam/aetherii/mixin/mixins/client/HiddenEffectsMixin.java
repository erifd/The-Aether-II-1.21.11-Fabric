package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.Collection;

@Mixin(Gui.class)
public class HiddenEffectsMixin {
    @ModifyExpressionValue(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getActiveEffects()Ljava/util/Collection;"), require = 0)
    private Collection<MobEffectInstance> visibleEffects(Collection<MobEffectInstance> effects) {
        return effects.stream().filter(effect -> !com.aetherteam.aetherii.client.AetherIIClientExtensions.hideEffect(effect)).toList();
    }
}
