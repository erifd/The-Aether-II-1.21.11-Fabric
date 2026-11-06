package com.aetherteam.nitrogen.loot.modifiers;

import com.aetherteam.nitrogen.Nitrogen;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class NitrogenLootModifiers {
    public static final ResourceKey<Registry<MapCodec<? extends IGlobalLootModifier>>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Nitrogen.MODID, "global_loot_modifiers"));
    public static final Registry<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final MapCodec<AddDungeonLootModifier> ADD_DUNGEON_LOOT = Registry.register(GLOBAL_LOOT_MODIFIERS, Identifier.fromNamespaceAndPath(Nitrogen.MODID, "add_dungeon_loot"), AddDungeonLootModifier.CODEC);
    public static final MapCodec<AddEntityDropsModifier> ADD_ENTITY_DROPS = Registry.register(GLOBAL_LOOT_MODIFIERS, Identifier.fromNamespaceAndPath(Nitrogen.MODID, "add_entity_drops"), AddEntityDropsModifier.CODEC);

    public static void init() {
        LootModifierManager.register();
    }
}
