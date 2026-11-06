package com.aetherteam.nitrogen.loot.modifiers;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.function.Predicate;

public abstract class LootModifier implements IGlobalLootModifier {
    protected final LootItemCondition[] conditions;
    private final Predicate<LootContext> combinedConditions;
    public final int priority;

    protected static <T extends LootModifier> Products.P2<RecordCodecBuilder.Mu<T>, LootItemCondition[], Integer> codecStart(RecordCodecBuilder.Instance<T> instance) {
        return instance.group(
            LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions),
            Codec.INT.optionalFieldOf("priority", IGlobalLootModifier.DEFAULT_PRIORITY).forGetter(lm -> lm.priority)
        );
    }

    protected LootModifier(LootItemCondition[] conditionsIn) {
        this(conditionsIn, DEFAULT_PRIORITY);
    }

    protected LootModifier(LootItemCondition[] conditionsIn, int priority) {
        this.conditions = conditionsIn;
        this.priority = priority;
        this.combinedConditions = AllOfCondition.allOf(List.of(conditionsIn));
    }

    @Override
    public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        return this.combinedConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
    }

    protected abstract ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context);
}
