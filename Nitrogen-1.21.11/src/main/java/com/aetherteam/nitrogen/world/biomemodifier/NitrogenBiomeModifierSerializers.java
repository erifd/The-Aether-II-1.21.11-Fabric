package com.aetherteam.nitrogen.world.biomemodifier;

import com.aetherteam.nitrogen.Nitrogen;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class NitrogenBiomeModifierSerializers {
    public static final ResourceKey<Registry<MapCodec<? extends BiomeModificationData>>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Nitrogen.MODID, "biome_modifier_serializers"));
    public static final Registry<MapCodec<? extends BiomeModificationData>> BIOME_MODIFIER_SERIALIZERS = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final MapCodec<AddMobChargeBiomeModifier> ADD_MOB_CHARGE_BIOME_MODIFIER_TYPE = Registry.register(BIOME_MODIFIER_SERIALIZERS, Identifier.fromNamespaceAndPath(Nitrogen.MODID, "add_mob_charge"), AddMobChargeBiomeModifier.CODEC);

    public static void init() {
        BiomeModifierManager.register();
    }
}
