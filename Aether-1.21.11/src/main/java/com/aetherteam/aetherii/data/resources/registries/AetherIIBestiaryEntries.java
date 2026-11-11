package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIIStats;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.api.guidebook.BestiaryEntry;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.attributes.AetherIIAttributes;
import com.aetherteam.aetherii.entity.attributes.EffectResistanceAttribute;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AetherIIBestiaryEntries {
    public static final ResourceKey<BestiaryEntry> FLYING_COW = createKey("flying_cow");
    public static final ResourceKey<BestiaryEntry> SHEEPUFF = createKey("sheepuff");
    public static final ResourceKey<BestiaryEntry> PHYG = createKey("phyg");
    public static final ResourceKey<BestiaryEntry> AERBUNNY = createKey("aerbunny");
    public static final ResourceKey<BestiaryEntry> HIGHFIELDS_TAEGORE = createKey("highfields_taegore");
    public static final ResourceKey<BestiaryEntry> MAGNETIC_TAEGORE = createKey("magnetic_taegore");
    public static final ResourceKey<BestiaryEntry> ARCTIC_TAEGORE = createKey("arctic_taegore");
    public static final ResourceKey<BestiaryEntry> HIGHFIELDS_BURRUKAI = createKey("highfields_burrukai");
    public static final ResourceKey<BestiaryEntry> MAGNETIC_BURRUKAI = createKey("magnetic_burrukai");
    public static final ResourceKey<BestiaryEntry> ARCTIC_BURRUKAI = createKey("arctic_burrukai");
    public static final ResourceKey<BestiaryEntry> HIGHFIELDS_KIRRID = createKey("highfields_kirrid");
    public static final ResourceKey<BestiaryEntry> MAGNETIC_KIRRID = createKey("magnetic_kirrid");
    public static final ResourceKey<BestiaryEntry> ARCTIC_KIRRID = createKey("arctic_kirrid");
    public static final ResourceKey<BestiaryEntry> MOA = createKey("moa");
    public static final ResourceKey<BestiaryEntry> SKYROOT_LIZARD = createKey("skyroot_lizard");
    public static final ResourceKey<BestiaryEntry> GLITTERWING = createKey("glitterwing");
    public static final ResourceKey<BestiaryEntry> SHROUDWING = createKey("shroudwing");
    public static final ResourceKey<BestiaryEntry> AECHOR_PLANT = createKey("aechor_plant");
    public static final ResourceKey<BestiaryEntry> CARRION_SPROUT = createKey("carrion_sprout");
    public static final ResourceKey<BestiaryEntry> ZEPHYR = createKey("zephyr");
    public static final ResourceKey<BestiaryEntry> BLUE_SWET = createKey("blue_swet");
    public static final ResourceKey<BestiaryEntry> GOLDEN_SWET = createKey("golden_swet");
    public static final ResourceKey<BestiaryEntry> SKEPHID = createKey("skephid");
    public static final ResourceKey<BestiaryEntry> TEMPEST = createKey("tempest");
    public static final ResourceKey<BestiaryEntry> COCKATRICE = createKey("cockatrice");
    public static final ResourceKey<BestiaryEntry> ARKENIUM_TALUTON = createKey("arkenium_taluton");
    public static final ResourceKey<BestiaryEntry> GRAVITITE_TALUTON = createKey("gravitite_taluton");
    public static final ResourceKey<BestiaryEntry> MIMIC = createKey("mimic");
    public static final ResourceKey<BestiaryEntry> DETONATION_SENTRY = createKey("detonation_sentry");
    public static final ResourceKey<BestiaryEntry> SENTRY_GOLEM = createKey("sentry_golem");
    public static final ResourceKey<BestiaryEntry> SLIDER = createKey("slider");
    public static final ResourceKey<BestiaryEntry> BLADESHROOM_HUNTER = createKey("bladeshroom_hunter");

    private static Holder<EntityType<?>> h(EntityType<?> type) {
        return type.builtInRegistryHolder();
    }

    public static final List<Holder<EntityType<?>>> ENTRY_ORDER = List.of(
            h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE), h(AetherIIEntityTypes.MAGNETIC_TAEGORE), h(AetherIIEntityTypes.ARCTIC_TAEGORE),
            h(AetherIIEntityTypes.HIGHFIELDS_KIRRID), h(AetherIIEntityTypes.MAGNETIC_KIRRID), h(AetherIIEntityTypes.ARCTIC_KIRRID),
            h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI), h(AetherIIEntityTypes.MAGNETIC_BURRUKAI), h(AetherIIEntityTypes.ARCTIC_BURRUKAI),
            h(AetherIIEntityTypes.PHYG), h(AetherIIEntityTypes.SHEEPUFF), h(AetherIIEntityTypes.FLYING_COW), h(AetherIIEntityTypes.AERBUNNY),
            h(AetherIIEntityTypes.SKYROOT_LIZARD), h(AetherIIEntityTypes.GLITTERWING), h(AetherIIEntityTypes.SHROUDWING),
            h(AetherIIEntityTypes.MOA),
            h(AetherIIEntityTypes.BLUE_SWET), h(AetherIIEntityTypes.GOLDEN_SWET), h(AetherIIEntityTypes.AECHOR_PLANT), h(AetherIIEntityTypes.CARRION_SPROUT),
            h(AetherIIEntityTypes.SKEPHID), h(AetherIIEntityTypes.ZEPHYR),
            h(AetherIIEntityTypes.TEMPEST), h(AetherIIEntityTypes.COCKATRICE),
            h(AetherIIEntityTypes.ARKENIUM_TALUTON), h(AetherIIEntityTypes.GRAVITITE_TALUTON),
            h(AetherIIEntityTypes.MIMIC), h(AetherIIEntityTypes.DETONATION_SENTRY), h(AetherIIEntityTypes.SENTRY_GOLEM), h(AetherIIEntityTypes.SLIDER)
    );

    public static final Map<ResourceKey<BestiaryEntry>, Holder<EntityType<?>>> ENTITIES = Map.ofEntries(
            Map.entry(FLYING_COW, h(AetherIIEntityTypes.FLYING_COW)),
            Map.entry(SHEEPUFF, h(AetherIIEntityTypes.SHEEPUFF)),
            Map.entry(PHYG, h(AetherIIEntityTypes.PHYG)),
            Map.entry(AERBUNNY, h(AetherIIEntityTypes.AERBUNNY)),
            Map.entry(HIGHFIELDS_TAEGORE, h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE)),
            Map.entry(MAGNETIC_TAEGORE, h(AetherIIEntityTypes.MAGNETIC_TAEGORE)),
            Map.entry(ARCTIC_TAEGORE, h(AetherIIEntityTypes.ARCTIC_TAEGORE)),
            Map.entry(HIGHFIELDS_BURRUKAI, h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI)),
            Map.entry(MAGNETIC_BURRUKAI, h(AetherIIEntityTypes.MAGNETIC_BURRUKAI)),
            Map.entry(ARCTIC_BURRUKAI, h(AetherIIEntityTypes.ARCTIC_BURRUKAI)),
            Map.entry(HIGHFIELDS_KIRRID, h(AetherIIEntityTypes.HIGHFIELDS_KIRRID)),
            Map.entry(MAGNETIC_KIRRID, h(AetherIIEntityTypes.MAGNETIC_KIRRID)),
            Map.entry(ARCTIC_KIRRID, h(AetherIIEntityTypes.ARCTIC_KIRRID)),
            Map.entry(MOA, h(AetherIIEntityTypes.MOA)),
            Map.entry(SKYROOT_LIZARD, h(AetherIIEntityTypes.SKYROOT_LIZARD)),
            Map.entry(GLITTERWING, h(AetherIIEntityTypes.GLITTERWING)),
            Map.entry(SHROUDWING, h(AetherIIEntityTypes.SHROUDWING)),
            Map.entry(AECHOR_PLANT, h(AetherIIEntityTypes.AECHOR_PLANT)),
            Map.entry(CARRION_SPROUT, h(AetherIIEntityTypes.CARRION_SPROUT)),
            Map.entry(ZEPHYR, h(AetherIIEntityTypes.ZEPHYR)),
            Map.entry(BLUE_SWET, h(AetherIIEntityTypes.BLUE_SWET)),
            Map.entry(GOLDEN_SWET, h(AetherIIEntityTypes.GOLDEN_SWET)),
            Map.entry(SKEPHID, h(AetherIIEntityTypes.SKEPHID)),
            Map.entry(TEMPEST, h(AetherIIEntityTypes.TEMPEST)),
            Map.entry(COCKATRICE, h(AetherIIEntityTypes.COCKATRICE)),
            Map.entry(ARKENIUM_TALUTON, h(AetherIIEntityTypes.ARKENIUM_TALUTON)),
            Map.entry(GRAVITITE_TALUTON, h(AetherIIEntityTypes.GRAVITITE_TALUTON)),
            Map.entry(MIMIC, h(AetherIIEntityTypes.MIMIC)),
            Map.entry(DETONATION_SENTRY, h(AetherIIEntityTypes.DETONATION_SENTRY)),
            Map.entry(SENTRY_GOLEM, h(AetherIIEntityTypes.SENTRY_GOLEM)),
            Map.entry(SLIDER, h(AetherIIEntityTypes.SLIDER))
//            Map.entry(BLADESHROOM_HUNTER, h(AetherIIEntityTypes.BLADESHROOM_HUNTER))
    );
    public static final List<Holder<EntityType<?>>> NAMED = List.of(
            h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE),
            h(AetherIIEntityTypes.MAGNETIC_TAEGORE),
            h(AetherIIEntityTypes.ARCTIC_TAEGORE),
            h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI),
            h(AetherIIEntityTypes.MAGNETIC_BURRUKAI),
            h(AetherIIEntityTypes.ARCTIC_BURRUKAI),
            h(AetherIIEntityTypes.HIGHFIELDS_KIRRID),
            h(AetherIIEntityTypes.MAGNETIC_KIRRID),
            h(AetherIIEntityTypes.ARCTIC_KIRRID)
    );
    public static final Map<Holder<EntityType<?>>, Double> SCALED = Map.ofEntries(
            Map.entry(h(AetherIIEntityTypes.ZEPHYR), 1.0)
    );
    public static final Map<Holder<EntityType<?>>, ImmutableMap<Holder<Attribute>, Double>> ATTRIBUTES = Map.ofEntries(
            Map.entry(h(AetherIIEntityTypes.AERBUNNY), AetherIIStats.AERBUNNY),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE), AetherIIStats.HIGHFIELDS_TAEGORE),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_TAEGORE), AetherIIStats.MAGNETIC_TAEGORE),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_TAEGORE), AetherIIStats.ARCTIC_TAEGORE),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI), AetherIIStats.HIGHFIELDS_BURRUKAI),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_BURRUKAI), AetherIIStats.MAGNETIC_BURRUKAI),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_BURRUKAI), AetherIIStats.ARCTIC_BURRUKAI),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_KIRRID), AetherIIStats.HIGHFIELDS_KIRRID),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_KIRRID), AetherIIStats.MAGNETIC_KIRRID),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_KIRRID), AetherIIStats.ARCTIC_KIRRID),
            Map.entry(h(AetherIIEntityTypes.MOA), AetherIIStats.MOA),
            Map.entry(h(AetherIIEntityTypes.SKYROOT_LIZARD), AetherIIStats.SKYROOT_LIZARD),
            Map.entry(h(AetherIIEntityTypes.GLITTERWING), AetherIIStats.GLITTERWING),
            Map.entry(h(AetherIIEntityTypes.SHROUDWING), AetherIIStats.SHROUDWING),
            Map.entry(h(AetherIIEntityTypes.AECHOR_PLANT), AetherIIStats.AECHOR_PLANT),
            Map.entry(h(AetherIIEntityTypes.CARRION_SPROUT), AetherIIStats.CARRION_SPROUT),
            Map.entry(h(AetherIIEntityTypes.ZEPHYR), AetherIIStats.ZEPHYR),
            Map.entry(h(AetherIIEntityTypes.TEMPEST), AetherIIStats.TEMPEST),
            Map.entry(h(AetherIIEntityTypes.COCKATRICE), AetherIIStats.COCKATRICE),
            Map.entry(h(AetherIIEntityTypes.BLUE_SWET), AetherIIStats.SWET),
            Map.entry(h(AetherIIEntityTypes.GOLDEN_SWET), AetherIIStats.SWET),
            Map.entry(h(AetherIIEntityTypes.SKEPHID), AetherIIStats.SKEPHID),
            Map.entry(h(AetherIIEntityTypes.ARKENIUM_TALUTON), AetherIIStats.ARKENIUM_TALUTON),
            Map.entry(h(AetherIIEntityTypes.GRAVITITE_TALUTON), AetherIIStats.GRAVITITE_TALUTON),
            Map.entry(h(AetherIIEntityTypes.MIMIC), AetherIIStats.MIMIC),
            Map.entry(h(AetherIIEntityTypes.DETONATION_SENTRY), AetherIIStats.DETONATION_SENTRY),
            Map.entry(h(AetherIIEntityTypes.SENTRY_GOLEM), AetherIIStats.SENTRY_GOLEM),
            Map.entry(h(AetherIIEntityTypes.SLIDER), AetherIIStats.SLIDER)
//            Map.entry(h(AetherIIEntityTypes.BLADESHROOM_HUNTER), AetherIIStats.BLADESHROOM_HUNTER)
    );
    public static final Map<Holder<EntityType<?>>, List<BestiaryEntry.LootDisplay>> LOOT = Map.ofEntries(
            Map.entry(h(AetherIIEntityTypes.FLYING_COW), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_RIB_CUT, 1.0, 1, 2))),
            Map.entry(h(AetherIIEntityTypes.SHEEPUFF), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.KIRRID_LOIN, 1.0, 1, 2), BestiaryEntry.LootDisplay.block(AetherIIBlocks.WHITE_CLOUDWOOL, 1.0, 1, 1))),
            Map.entry(h(AetherIIEntityTypes.PHYG), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.RAW_TAEGORE_MEAT, 1.0, 1, 2))),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.RAW_TAEGORE_MEAT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_TAEGORE), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.RAW_TAEGORE_MEAT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_TAEGORE), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.RAW_TAEGORE_MEAT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_RIB_CUT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 0, 2), BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_PLATE, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_BURRUKAI), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_RIB_CUT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 0, 2), BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_PLATE, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_BURRUKAI), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_RIB_CUT, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.BEAST_PELT, 1.0, 0, 2), BestiaryEntry.LootDisplay.item(AetherIIItems.BURRUKAI_PLATE, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_KIRRID), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.KIRRID_LOIN, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.CLOUDWOOL, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_KIRRID), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.KIRRID_LOIN, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.CLOUDWOOL, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_KIRRID), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.KIRRID_LOIN, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.CLOUDWOOL, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.MOA), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.MOA_FEATHER, 1.0, 0, 2))),
            Map.entry(h(AetherIIEntityTypes.AECHOR_PLANT), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.AECHOR_PETAL, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.AECHOR_CUTTING, 1.0, 0, 1))),
            Map.entry(h(AetherIIEntityTypes.CARRION_SPROUT), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.WYNDBERRY, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.CARRION_CUTTING, 1.0, 0, 1))),
            Map.entry(h(AetherIIEntityTypes.ZEPHYR), List.of(BestiaryEntry.LootDisplay.block(AetherIIBlocks.COLD_AERCLOUD, 1.0, 1, 3), BestiaryEntry.LootDisplay.block(AetherIIBlocks.BLUE_AERCLOUD, 0.1111, 1, 2))), //, BestiaryEntry.LootDisplay.item(AetherIIItems.ZEPHYR_HUSK, 0.075, 1, 1)
            Map.entry(h(AetherIIEntityTypes.BLUE_SWET), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.SWET_GEL, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.SWET_SUGAR, 1.0, 0, 1))),
            Map.entry(h(AetherIIEntityTypes.GOLDEN_SWET), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.SWET_GEL, 1.0, 1, 3), BestiaryEntry.LootDisplay.item(AetherIIItems.SWET_SUGAR, 1.0, 2, 3))),
            Map.entry(h(AetherIIEntityTypes.SKEPHID), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.CLOUDTWINE, 1.0, 1, 2))),
            Map.entry(h(AetherIIEntityTypes.TEMPEST), List.of(BestiaryEntry.LootDisplay.block(AetherIIBlocks.STORM_AERCLOUD, 1.0, 1, 3))), //, BestiaryEntry.LootDisplay.item(AetherIIItems.CHARGE_CATALYST, 0.075, 1, 1)
            Map.entry(h(AetherIIEntityTypes.COCKATRICE), List.of(BestiaryEntry.LootDisplay.item(AetherIIItems.COCKATRICE_FEATHER, 1.0, 1, 3))),
            Map.entry(h(AetherIIEntityTypes.ARKENIUM_TALUTON), List.of(BestiaryEntry.LootDisplay.block(AetherIIBlocks.HOLYSTONE, 1.0, 0, 2))), //, BestiaryEntry.LootDisplay.item(AetherIIItems.ARKENIUM_CORE, 0.075, 1, 1)
            Map.entry(h(AetherIIEntityTypes.GRAVITITE_TALUTON), List.of(BestiaryEntry.LootDisplay.block(AetherIIBlocks.HOLYSTONE, 1.0, 0, 2))), //, BestiaryEntry.LootDisplay.item(AetherIIItems.GRAVITITE_CORE, 0.075, 1, 1)
            Map.entry(h(AetherIIEntityTypes.MIMIC), List.of()), //BestiaryEntry.LootDisplay.item(AetherIIItems.EYE_OF_THE_MIMIC, 0.075, 1, 1)
            Map.entry(h(AetherIIEntityTypes.DETONATION_SENTRY), List.of()),
            Map.entry(h(AetherIIEntityTypes.SENTRY_GOLEM), List.of()),
            Map.entry(h(AetherIIEntityTypes.SLIDER), List.of())
//            Map.entry(h(AetherIIEntityTypes.BLADESHROOM_HUNTER), List.of())
    );
    public static final Map<Holder<EntityType<?>>, TagKey<Item>> FED = Map.ofEntries(
            Map.entry(h(AetherIIEntityTypes.FLYING_COW), AetherIITags.Items.FLYING_COW_FOOD),
            Map.entry(h(AetherIIEntityTypes.SHEEPUFF), AetherIITags.Items.SHEEPUFF_FOOD),
            Map.entry(h(AetherIIEntityTypes.PHYG), AetherIITags.Items.PHYG_FOOD),
            Map.entry(h(AetherIIEntityTypes.AERBUNNY), AetherIITags.Items.AERBUNNY_FOOD),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_TAEGORE), AetherIITags.Items.TAEGORE_FOOD),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_TAEGORE), AetherIITags.Items.TAEGORE_FOOD),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_TAEGORE), AetherIITags.Items.TAEGORE_FOOD),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI), AetherIITags.Items.BURRUKAI_FOOD),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_BURRUKAI), AetherIITags.Items.BURRUKAI_FOOD),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_BURRUKAI), AetherIITags.Items.BURRUKAI_FOOD),
            Map.entry(h(AetherIIEntityTypes.HIGHFIELDS_KIRRID), AetherIITags.Items.KIRRID_FOOD),
            Map.entry(h(AetherIIEntityTypes.MAGNETIC_KIRRID), AetherIITags.Items.KIRRID_FOOD),
            Map.entry(h(AetherIIEntityTypes.ARCTIC_KIRRID), AetherIITags.Items.KIRRID_FOOD),
            Map.entry(h(AetherIIEntityTypes.MOA), AetherIITags.Items.MOA_FOOD)
    );

    private static ResourceKey<BestiaryEntry> createKey(String name) {
        return ResourceKey.create(AetherIIRegistries.BESTIARY_ENTRY, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void bootstrap(BootstrapContext<BestiaryEntry> context) {
        for (Map.Entry<ResourceKey<BestiaryEntry>, Holder<EntityType<?>>> entry : ENTITIES.entrySet()) {
            Holder<EntityType<?>> holder = entry.getValue();
            EntityType<?> entity = holder.value();
            String name = NAMED.contains(holder) ? "aether_ii.guidebook_bestiary.name.entity.aether_ii." + entity.toShortString() : entity.getDescriptionId();
            String slotName = NAMED.contains(holder) ? "aether_ii.guidebook_bestiary.slot_name.entity.aether_ii." + entity.toShortString() : entity.getDescriptionId();
            Optional<String> slotSubtitle = NAMED.contains(holder) ? Optional.of("aether_ii.guidebook_bestiary.slot_subtitle.entity.aether_ii." + entity.toShortString()) : Optional.empty();
            double health = ATTRIBUTES.containsKey(holder) ? ATTRIBUTES.get(holder).getOrDefault(Attributes.MAX_HEALTH, 0.0) : 0.0;
            double slashDefense = ATTRIBUTES.containsKey(holder) ? ATTRIBUTES.get(holder).getOrDefault(AetherIIAttributes.SLASH_RESISTANCE, 0.0) : 0.0;
            double impactDefense = ATTRIBUTES.containsKey(holder) ? ATTRIBUTES.get(holder).getOrDefault(AetherIIAttributes.IMPACT_RESISTANCE, 0.0) : 0.0;
            double pierceDefense = ATTRIBUTES.containsKey(holder) ? ATTRIBUTES.get(holder).getOrDefault(AetherIIAttributes.PIERCE_RESISTANCE, 0.0) : 0.0;
            List<BestiaryEntry.EffectResistanceDisplay> effectResistances = getEffectResistances(holder);
            Optional<Double> scaleMultiplier = SCALED.containsKey(holder) ? Optional.of(SCALED.get(holder)) : Optional.empty();
            List<BestiaryEntry.LootDisplay> loot = LOOT.containsKey(holder) ? LOOT.get(holder) : new ArrayList<>();
            Optional<TagKey<Item>> food = FED.containsKey(holder) ? Optional.of(FED.get(holder)) : Optional.empty();

            context.register(entry.getKey(), new BestiaryEntry(
                    entry.getKey().identifier(),
                    Identifier.fromNamespaceAndPath(AetherII.MODID, "guidebook/bestiary/" + entity.toShortString()),
                    name,
                    slotName,
                    slotSubtitle,
                    "aether_ii.guidebook_bestiary.description.entity.aether_ii." + entity.toShortString(),
                    holder,
                    (int) health,
                    (int) slashDefense,
                    (int) impactDefense,
                    (int) pierceDefense,
                    effectResistances,
                    scaleMultiplier,
                    loot,
                    food
            ));
        }
    }

    public static Registry<BestiaryEntry> getRegistry(RegistryAccess registryAccess) {
        return registryAccess.lookupOrThrow(AetherIIRegistries.BESTIARY_ENTRY);
    }

    public static List<BestiaryEntry.EffectResistanceDisplay> getEffectResistances(Holder<EntityType<?>> holder) {
        ArrayList<BestiaryEntry.EffectResistanceDisplay> effectResistances = new ArrayList<>();
        if (ATTRIBUTES.containsKey(holder)) {
            for (Map.Entry<Holder<Attribute>, Double> attribute : ATTRIBUTES.get(holder).entrySet()) {
                if (attribute.getKey().value() instanceof EffectResistanceAttribute) {
                    effectResistances.add(new BestiaryEntry.EffectResistanceDisplay(attribute.getKey(), attribute.getValue().intValue()));
                }
            }
        }
        return effectResistances;
    }

    public static Map<EntityType<?>, TagKey<Item>> getFedEntityTypes() {
         return AetherIIBestiaryEntries.FED.entrySet().stream().collect(Collectors.toMap((e) -> e.getKey().value(), Map.Entry::getValue));
    }
}
