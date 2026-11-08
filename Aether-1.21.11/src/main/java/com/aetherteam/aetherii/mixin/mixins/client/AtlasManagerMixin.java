package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.resources.Identifier;
import net.minecraft.client.resources.model.AtlasManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(AtlasManager.class)
public class AtlasManagerMixin {
    @Shadow
    @Final
    @Mutable
    private static List<AtlasManager.AtlasConfig> KNOWN_ATLASES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void aetherii$addAtlases(CallbackInfo ci) {
        List<AtlasManager.AtlasConfig> list = new ArrayList<>(KNOWN_ATLASES);
        for (String name : new String[]{"moa_feather", "moa_eyes", "moa_keratin", "armor_styles", "alkahest_purifier", "sentry_crate", "sentry_spawner", "sage_chest"}) {
            list.add(new AtlasManager.AtlasConfig(Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/atlas/" + name + ".png"), Identifier.fromNamespaceAndPath(AetherII.MODID, name), false));
        }
        KNOWN_ATLASES = list;
    }
}
