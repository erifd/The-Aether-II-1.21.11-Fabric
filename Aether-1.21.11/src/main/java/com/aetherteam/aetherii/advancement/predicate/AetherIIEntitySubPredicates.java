package com.aetherteam.aetherii.advancement.predicate;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class AetherIIEntitySubPredicates {
    private static <T extends EntitySubPredicate> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static final MapCodec<OnGroundPredicate> ON_GROUND = register("on_ground", OnGroundPredicate.CODEC);
    public static final MapCodec<AlivePredicate> ALIVE = register("alive", AlivePredicate.CODEC);
    public static final MapCodec<ArmorSetPredicate> ARMOR_SET = register("armor_set", ArmorSetPredicate.CODEC);
    public static final MapCodec<EffectBuildupPredicate> EFFECT_BUILDUP = register("effect_buildup", EffectBuildupPredicate.CODEC);
    public static final MapCodec<KirridPredicate> KIRRID = register("kirrid", KirridPredicate.CODEC);
    public static final MapCodec<SheepuffPredicate> SHEEPUFF = register("sheepuff", SheepuffPredicate.CODEC);

    public static void init() {}
}
