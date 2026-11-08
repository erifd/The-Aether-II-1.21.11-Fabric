package com.aetherteam.aetherii.data.generators.tags;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import com.aetherteam.aetherii.data.providers.AetherKeyTagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class AetherIIDamageTypeTagData extends FabricTagProvider<DamageType> {
    public AetherIIDamageTypeTagData(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.DAMAGE_TYPE, registries);
    }

    protected AetherKeyTagAppender<DamageType> tag(TagKey<DamageType> key) {
        return new AetherKeyTagAppender<>(this.getOrCreateRawBuilder(key));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(AetherIITags.DamageTypes.TYPED).add(
                DamageTypes.PLAYER_ATTACK,
                DamageTypes.ARROW,
                DamageTypes.TRIDENT,
                DamageTypes.THROWN);

        this.tag(DamageTypeTags.NO_KNOCKBACK).add(
                AetherIIDamageTypes.PLAYER_AOE_NO_KNOCKBACK,
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.FRACTURE,
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM,
                AetherIIDamageTypes.IMMOLATION,
                AetherIIDamageTypes.ALKAHEST,
                AetherIIDamageTypes.CARRION_SPROUT);
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.FRACTURE,
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM);
        this.tag(DamageTypeTags.IS_LIGHTNING).add(AetherIIDamageTypes.CHARGED, AetherIIDamageTypes.SHOCK);
        this.tag(DamageTypeTags.IS_FIRE).add(AetherIIDamageTypes.IMMOLATION);
    }
}