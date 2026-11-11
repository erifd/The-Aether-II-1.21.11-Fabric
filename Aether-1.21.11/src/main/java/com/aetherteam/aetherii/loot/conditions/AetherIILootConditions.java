package com.aetherteam.aetherii.loot.conditions;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class AetherIILootConditions {
    private static LootItemConditionType register(String name, MapCodec<? extends LootItemCondition> codec) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new LootItemConditionType(codec));
    }

    public static final LootItemConditionType PLAYER_GROWN = register("player_grown", PlayerGrownCondition.CODEC);
    public static final LootItemConditionType TIER_COMPARE = register("tier_compare", TierCompare.CODEC);

    public static void init() {}
}

