package com.aetherteam.aetherii.data.generators.tags;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.data.resources.registries.AetherIIStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import com.aetherteam.aetherii.data.providers.AetherKeyTagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class AetherIIStructureTagData extends FabricTagProvider<Structure> {
    public AetherIIStructureTagData(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.STRUCTURE, registries);
    }

    protected AetherKeyTagAppender<Structure> tag(TagKey<Structure> key) {
        return new AetherKeyTagAppender<>(this.getOrCreateRawBuilder(key));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(AetherIITags.Structures.OUTPOSTS).add(
                AetherIIStructures.OUTPOST
        );
        this.tag(AetherIITags.Structures.CAMPS).add(
                AetherIIStructures.CAMP_HIGHFIELDS,
                AetherIIStructures.CAMP_MAGNETIC,
                AetherIIStructures.CAMP_ARCTIC
        );
        this.tag(AetherIITags.Structures.DUNGEONS).add(
                AetherIIStructures.SENTRY_RUINS,
                AetherIIStructures.INFECTED_GUARDIAN_TREE
        );
        this.tag(AetherIITags.Structures.WATCHTOWERS).add(
                AetherIIStructures.WATCHTOWER
        );
        this.tag(AetherIITags.Structures.SURFACE_RUINS).add(
                AetherIIStructures.VERADEXIAN_RUINS_TEMPERATE,
                AetherIIStructures.VERADEXIAN_RUINS_ARCTIC,
                AetherIIStructures.VERADEXIAN_LIBRARY_TEMPERATE
        );

        this.tag(AetherIITags.Structures.TREE_BLACKLIST_FILTER).addTag(AetherIITags.Structures.CAMPS);
        this.tag(AetherIITags.Structures.ALKAHEST_POOL_BLACKLIST_FILTER).addTag(AetherIITags.Structures.DUNGEONS);
        this.tag(AetherIITags.Structures.COAST_BLACKLIST_FILTER).add(AetherIIStructures.SENTRY_RUINS);
        this.tag(AetherIITags.Structures.FERROSITE_SPIKE_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.OUTPOSTS,
                AetherIITags.Structures.CAMPS,
                AetherIITags.Structures.WATCHTOWERS
        );
        this.tag(AetherIITags.Structures.ARCTIC_ICE_SPIKE_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.OUTPOSTS,
                AetherIITags.Structures.CAMPS,
                AetherIITags.Structures.WATCHTOWERS
        );
        this.tag(AetherIITags.Structures.AERCLOUD_BLACKLIST_FILTER).addTag(AetherIITags.Structures.DUNGEONS);
    }
}