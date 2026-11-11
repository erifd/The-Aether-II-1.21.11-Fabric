package com.aetherteam.aetherii.data;

import com.aetherteam.aetherii.data.generators.loot.*;
import com.aetherteam.aetherii.data.providers.AetherIILootProvider;
import com.aetherteam.aetherii.loot.AetherIILootContexts;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import com.aetherteam.aetherii.data.generators.*;
import com.aetherteam.aetherii.data.generators.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AetherIIData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Client Data
        pack.addProvider((FabricDataOutput output) -> new AetherIIModelData(output));
        pack.addProvider((FabricDataOutput output) -> new AetherIIParticleData(output));
        pack.addProvider(AetherIILanguageData::new);
        pack.addProvider((FabricDataOutput output) -> new AetherIISoundData(output));
        pack.addProvider((FabricDataOutput output) -> new AetherIIEquipmentAssetData(output));

        // Server Data
        pack.addProvider(AetherIIRegistrySets::new);
        pack.addProvider(AetherIIRecipeData.Runner::new);
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, LootContextParamSets.BLOCK, "Block", AetherIIBlockLoot::new));
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, LootContextParamSets.ENTITY, "Entity", AetherIIEntityLoot::new));
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, LootContextParamSets.CHEST, "Chest", AetherIIChestLoot::new));
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, LootContextParamSets.SHEARING, "Shearing", AetherIIShearingLoot::new));
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, LootContextParamSets.GIFT, "Gift", AetherIIGiftLoot::new));
        pack.addProvider((output, registries) -> new AetherIILootProvider(output, registries, AetherIILootContexts.STRIPPING, "Stripping", AetherIIStrippingLoot::new));
        pack.addProvider(AetherIILootModifierData::new);
        pack.addProvider(AetherIIAdvancementData::new);
        pack.addProvider(AetherIIDataMapData::new);

        // Tags
        pack.addProvider(AetherIIBlockTagData::new);
        pack.addProvider(AetherIIItemTagData::new);
        pack.addProvider(AetherIIEntityTypeTagData::new);
        pack.addProvider(AetherIIFluidTagData::new);
        pack.addProvider(AetherIIBiomeTagData::new);
        pack.addProvider(AetherIIStructureTagData::new);
        pack.addProvider(AetherIIDamageTypeTagData::new);
        pack.addProvider(AetherIIMobEffectTagData::new);
        pack.addProvider(AetherIISoundEventTagData::new);

        // pack.mcmeta
        pack.addProvider((FabricDataOutput output) -> new PackMetadataGenerator(output).add(PackMetadataSection.CLIENT_TYPE, new PackMetadataSection(
                Component.translatable("pack.aether_ii.mod.description"),
                new InclusiveRange<>(DetectedVersion.BUILT_IN.packVersion(PackType.CLIENT_RESOURCES)))));
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        AetherIIRegistrySets.build(builder);
    }
}
