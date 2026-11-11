package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.data.generators.models.AetherIIBlockModels;
import com.aetherteam.aetherii.data.generators.models.AetherIIItemModels;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class AetherIIModelData extends FabricModelProvider {
    public AetherIIModelData(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        new AetherIIBlockModels(generators.blockStateOutput, generators.itemModelOutput, generators.modelOutput).run();
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        new AetherIIItemModels(generators.itemModelOutput, generators.modelOutput).run();
    }
}
