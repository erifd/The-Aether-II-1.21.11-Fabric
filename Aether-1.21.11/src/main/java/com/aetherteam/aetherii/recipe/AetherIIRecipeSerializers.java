package com.aetherteam.aetherii.recipe;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.recipe.recipes.block.*;
import com.aetherteam.aetherii.recipe.recipes.item.AlkahestPurificationRecipe;
import com.aetherteam.aetherii.recipe.recipes.item.AltarEnchantingRecipe;
import com.aetherteam.aetherii.recipe.recipes.item.HourglassRestoringRecipe;
import com.aetherteam.aetherii.recipe.recipes.item.special.LootRepairRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class AetherIIRecipeSerializers {
    public static final RecipeSerializer<AmbrosiumRecipe> AMBROSIUM_ENCHANTING = register("ambrosium_enchanting", new AmbrosiumRecipe.Serializer());
    public static final RecipeSerializer<IrradiationRecipe> DUST_IRRADIATION = register("dust_irradiation", new IrradiationRecipe.Serializer());
    public static final RecipeSerializer<AlkahestCorrosionRecipe> ALKAHEST_CORROSION = register("alkahest_corrosion", new AlkahestCorrosionRecipe.Serializer());
    public static final RecipeSerializer<SwetGelRecipe> SWET_GEL_CONVERSION = register("swet_gel_conversion", new SwetGelRecipe.Serializer());
    public static final RecipeSerializer<IcestoneFreezableRecipe> ICESTONE_FREEZABLE = register("icestone_freezable", new IcestoneFreezableRecipe.Serializer());
    public static final RecipeSerializer<AccessoryFreezableRecipe> ACCESSORY_FREEZABLE = register("accessory_freezable", new AccessoryFreezableRecipe.Serializer());

    public static final RecipeSerializer<HourglassRestoringRecipe> HOURGLASS_RESTORING = register("hourglass_restoring", new HourglassRestoringRecipe.Serializer());
    public static final RecipeSerializer<AltarEnchantingRecipe> ALTAR_ENCHANTING = register("altar_enchanting", new AltarEnchantingRecipe.Serializer());
    public static final RecipeSerializer<AlkahestPurificationRecipe> ALKAHEST_PURIFICATION = register("alkahest_purification", new AlkahestPurificationRecipe.Serializer());

    public static final RecipeSerializer<LootRepairRecipe> LOOT_REPAIR = register("loot_repair", new net.minecraft.world.item.crafting.CustomRecipe.Serializer<>(LootRepairRecipe::new));

    private static <T extends RecipeSerializer<?>> T register(String name, T serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(AetherII.MODID, name), serializer);
    }

    public static void init() {}
}
