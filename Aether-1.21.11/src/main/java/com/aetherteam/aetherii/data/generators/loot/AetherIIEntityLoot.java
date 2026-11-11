package com.aetherteam.aetherii.data.generators.loot;

import com.aetherteam.aetherii.advancement.predicate.KirridPredicate;
import com.aetherteam.aetherii.advancement.predicate.SheepuffPredicate;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.passive.Kirrid;
import com.aetherteam.aetherii.entity.passive.Sheepuff;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.loot.AetherIILoot;
import com.aetherteam.aetherii.loot.conditions.PlayerGrownCondition;
import com.aetherteam.aetherii.loot.functions.GelDropsFunction;
import com.aetherteam.aetherii.loot.functions.SugarDropsFunction;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class AetherIIEntityLoot extends EntityLootSubProvider {
    /** Our own record of every table added, because {@link EntityLootSubProvider}'s own map is private. */
    private final Map<EntityType<?>, Map<ResourceKey<LootTable>, LootTable.Builder>> tables = new HashMap<>();

    public AetherIIEntityLoot(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void add(EntityType<?> entityType, ResourceKey<LootTable> key, LootTable.Builder builder) {
        this.tables.computeIfAbsent(entityType, type -> new HashMap<>()).put(key, builder);
    }

    @Override
    public void generate() {
        this.add(AetherIIEntityTypes.FLYING_COW, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_RIB_CUT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.PHYG, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.RAW_TAEGORE_MEAT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.AERBUNNY, LootTable.lootTable());
        this.add(AetherIIEntityTypes.AERWHALE, LootTable.lootTable());

        Sheepuff.SheepuffColor.CLOUDWOOL_BY_SHEEPUFF_COLOR.forEach((color, itemLike) -> this.add(AetherIIEntityTypes.SHEEPUFF, AetherIILoot.ENTITIES_SHEEPUFF_WOOL_BY_DYE.get(color), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(SheepuffPredicate.isPuffed(false)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).add(LootItem.lootTableItem(itemLike)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(SheepuffPredicate.isPuffed(true))))))
        );

        this.add(AetherIIEntityTypes.SHEEPUFF, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.KIRRID_LOIN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(createSheepuffDispatchPool(AetherIILoot.ENTITIES_SHEEPUFF_WOOL_BY_DYE))
        );

        this.add(AetherIIEntityTypes.HIGHFIELDS_TAEGORE, this.createTaegoreTable());
        this.add(AetherIIEntityTypes.MAGNETIC_TAEGORE, this.createTaegoreTable());
        this.add(AetherIIEntityTypes.ARCTIC_TAEGORE, this.createTaegoreTable());

        this.add(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI, this.createBurrukaiTable());
        this.add(AetherIIEntityTypes.MAGNETIC_BURRUKAI, this.createBurrukaiTable());
        this.add(AetherIIEntityTypes.ARCTIC_BURRUKAI, this.createBurrukaiTable());

        Kirrid.KirridColor.CLOUDWOOL_BY_KIRRID_COLOR.forEach((color, lootTable) -> {
            this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
            this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
            this.add(AetherIIEntityTypes.ARCTIC_KIRRID, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
        });

        this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        this.add(AetherIIEntityTypes.ARCTIC_KIRRID, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));

        this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_UNDYED));
        this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_UNDYED));
        this.add(AetherIIEntityTypes.ARCTIC_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_UNDYED));

        this.add(AetherIIEntityTypes.MOA, LootTable.lootTable());

        this.add(AetherIIEntityTypes.SKYROOT_LIZARD, LootTable.lootTable());

        this.add(AetherIIEntityTypes.GLITTERWING, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SHROUDWING, LootTable.lootTable());

        this.add(AetherIIEntityTypes.AECHOR_PLANT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.AECHOR_PETAL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.AECHOR_CUTTING)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(PlayerGrownCondition::new)
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.AECHOR_CUTTING)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.35F, 0.1F))
                                .when(InvertedLootItemCondition.invert(PlayerGrownCondition::new))
                        )
                )
        );
        this.add(AetherIIEntityTypes.CARRION_SPROUT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.WYNDBERRY)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.CARRION_CUTTING)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(PlayerGrownCondition::new)
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.CARRION_CUTTING)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.35F, 0.1F))
                                .when(InvertedLootItemCondition.invert(PlayerGrownCondition::new))
                        )
                )
        );

        this.add(AetherIIEntityTypes.ZEPHYR, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.COLD_AERCLOUD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.BLUE_AERCLOUD)
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.1111F, 0.1111F))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)) //todo
//                        .add(LootItem.lootTableItem(AetherIIItems.ZEPHYR_HUSK)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.TEMPEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.STORM_AERCLOUD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)) //todo
//                        .add(LootItem.lootTableItem(AetherIIItems.CHARGE_CATALYST)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.COCKATRICE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.COCKATRICE_FEATHER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.BLUE_SWET, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_GEL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(GelDropsFunction.extra(ConstantValue.exactly(1.0F))
                                )
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_SUGAR)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                .apply(SugarDropsFunction.extra(ConstantValue.exactly(1.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.GOLDEN_SWET, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_GEL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                .apply(GelDropsFunction.extra(ConstantValue.exactly(1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_SUGAR)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                .apply(SugarDropsFunction.extra(ConstantValue.exactly(2.0F)))
                        )
                )
        );

        this.add(AetherIIEntityTypes.SKEPHID, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.CLOUDTWINE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))
                                )
                        )
                ));

        this.add(AetherIIEntityTypes.ARKENIUM_TALUTON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.HOLYSTONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)) //todo
//                        .add(LootItem.lootTableItem(AetherIIItems.ARKENIUM_CORE)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.075F, 0.025F))
//                        )
//                )
        );
        this.add(AetherIIEntityTypes.GRAVITITE_TALUTON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.HOLYSTONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)) //todo
//                        .add(LootItem.lootTableItem(AetherIIItems.GRAVITITE_CORE)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.BLADESHROOM_HUNTER, LootTable.lootTable());

        this.add(AetherIIEntityTypes.MIMIC, LootTable.lootTable()
//                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)) //todo
//                        .add(LootItem.lootTableItem(AetherIIItems.EYE_OF_THE_MIMIC)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.DETONATION_SENTRY, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SENTRY_GOLEM, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SLIDER, LootTable.lootTable());

        this.add(AetherIIEntityTypes.EDWARD, LootTable.lootTable());
    }

    protected LootTable.Builder createTaegoreTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.RAW_TAEGORE_MEAT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BEAST_PELT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                );
    }

    protected LootTable.Builder createBurrukaiTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_RIB_CUT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BEAST_PELT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_PLATE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                );
    }

    protected LootTable.Builder createKirridTable(Map<Kirrid.KirridColor, ResourceKey<LootTable>> wool, ResourceKey<LootTable> undyed) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.KIRRID_LOIN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(createKirridDispatchPool(wool, undyed));
    }

    public static LootPool.Builder createKirridDispatchPool(Map<Kirrid.KirridColor, ResourceKey<LootTable>> map, ResourceKey<LootTable> undyed) {
        AlternativesEntry.Builder builder = AlternativesEntry.alternatives();
        Map.Entry<Kirrid.KirridColor, ResourceKey<LootTable>> entry;
        for (Iterator<Map.Entry<Kirrid.KirridColor, ResourceKey<LootTable>>> var2 = map.entrySet().iterator(); var2.hasNext(); builder = builder.otherwise(NestedLootTable.lootTableReference(entry.getValue()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(KirridPredicate.hasWool(entry.getKey())))))) {
            entry = var2.next();
        }
        builder = builder.otherwise(NestedLootTable.lootTableReference(undyed).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(KirridPredicate.hasWool()))));
        return LootPool.lootPool().add(builder);
    }

    public static LootPool.Builder createSheepuffDispatchPool(Map<Sheepuff.SheepuffColor, ResourceKey<LootTable>> map) {
        AlternativesEntry.Builder builder = AlternativesEntry.alternatives();
        Map.Entry<Sheepuff.SheepuffColor, ResourceKey<LootTable>> entry;
        for (Iterator<Map.Entry<Sheepuff.SheepuffColor, ResourceKey<LootTable>>> var2 = map.entrySet().iterator(); var2.hasNext(); builder = builder.otherwise(NestedLootTable.lootTableReference(entry.getValue()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(SheepuffPredicate.hasWool(entry.getKey())))))) {
            entry = var2.next();
        }
        return LootPool.lootPool().add(builder);
    }

    @Override
    public void generate(java.util.function.BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        this.generate();
        this.tables.values().forEach(entityTables -> entityTables.forEach(consumer));
    }
}
