package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.data.resources.registries.*;
import com.aetherteam.aetherii.data.resources.registries.pools.AetherIIPools;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.slf4j.Logger;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Exports every entry the mod adds to the dynamic registries (built by {@link #build} through
 * {@link com.aetherteam.aetherii.data.AetherIIData#buildRegistry}).
 */
public class AetherIIRegistrySets extends FabricDynamicRegistryProvider {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static RegistrySetBuilder build(RegistrySetBuilder builder) {
        return builder
                .add(Registries.BIOME, AetherIIBiomes::bootstrap)
                .add(Registries.DENSITY_FUNCTION, AetherIIDensityFunctions::bootstrap)
                .add(Registries.NOISE, AetherIINoises::bootstrap)
                .add(Registries.NOISE_SETTINGS, AetherIINoiseSettings::bootstrap)
                .add(Registries.DIMENSION_TYPE, AetherIIDimensions::bootstrapDimensionType)
                .add(Registries.LEVEL_STEM, AetherIIDimensions::bootstrapLevelStem)
                .add(Registries.CONFIGURED_FEATURE, AetherIIConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, AetherIIPlacedFeatures::bootstrap)
                .add(Registries.CONFIGURED_CARVER, AetherIICarvers::bootstrap)
                .add(Registries.STRUCTURE, AetherIIStructures::bootstrap)
                .add(Registries.STRUCTURE_SET, AetherIIStructureSets::bootstrap)
                .add(Registries.TEMPLATE_POOL, AetherIIPools::bootstrap)
                .add(Registries.PROCESSOR_LIST, AetherIIProcessorLists::bootstrap)
                .add(Registries.DAMAGE_TYPE, AetherIIDamageTypes::bootstrap)
                .add(Registries.JUKEBOX_SONG, AetherIIJukeboxSongs::bootstrap)
                .add(AetherIIRegistries.BESTIARY_ENTRY, AetherIIBestiaryEntries::bootstrap)
                .add(AetherIIRegistries.EFFECTS_ENTRY, AetherIIEffectsEntries::bootstrap)
                .add(AetherIIRegistries.EXPLORATION_ENTRY, AetherIIExplorationEntries::bootstrap)
                .add(AetherIIRegistries.STYLE_DESIGN, AetherIIStyleDesigns::bootstrap)
                .add(AetherIIRegistries.STYLE_MATERIAL, AetherIIStyleMaterials::bootstrap)
                .add(AetherIIRegistries.ITEM_REINFORCEMENT, AetherIIItemReinforcements::bootstrap)
                .add(AetherIIRegistries.SKYROOT_LIZARD_VARIANT, AetherIISkyrootLizardVariants::bootstrap)
                .add(AetherIIRegistries.GLITTERWING_VARIANT, AetherIIGlitterwingVariants::bootstrap)
                .add(AetherIIRegistries.SHROUDWING_VARIANT, AetherIIShroudwingVariants::bootstrap)
                .add(AetherIIRegistries.REWARD_WRAPPER, AetherIIRewardWrappers::bootstrap);
    }

    /** Every registry {@link #build} adds to; {@link net.minecraft.core.RegistrySetBuilder} keeps its keys private. */
    public static final List<ResourceKey<? extends Registry<?>>> REGISTRIES = List.of(
            Registries.BIOME,
            Registries.DENSITY_FUNCTION,
            Registries.NOISE,
            Registries.NOISE_SETTINGS,
            Registries.DIMENSION_TYPE,
            // Registries.LEVEL_STEM (minecraft:dimension) is not a worldgen registry, so Fabric's provider rejects it.
            // Its JSON lives in the data pack under data/aether_ii/dimension/ and is not exported by this provider.
            Registries.CONFIGURED_FEATURE,
            Registries.PLACED_FEATURE,
            Registries.CONFIGURED_CARVER,
            Registries.STRUCTURE,
            Registries.STRUCTURE_SET,
            Registries.TEMPLATE_POOL,
            Registries.PROCESSOR_LIST,
            Registries.DAMAGE_TYPE,
            Registries.JUKEBOX_SONG,
            AetherIIRegistries.BESTIARY_ENTRY,
            AetherIIRegistries.EFFECTS_ENTRY,
            AetherIIRegistries.EXPLORATION_ENTRY,
            AetherIIRegistries.STYLE_DESIGN,
            AetherIIRegistries.STYLE_MATERIAL,
            AetherIIRegistries.ITEM_REINFORCEMENT,
            AetherIIRegistries.SKYROOT_LIZARD_VARIANT,
            AetherIIRegistries.GLITTERWING_VARIANT,
            AetherIIRegistries.SHROUDWING_VARIANT,
            AetherIIRegistries.REWARD_WRAPPER
    );

    public AetherIIRegistrySets(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        for (ResourceKey<? extends Registry<?>> key : REGISTRIES) {
            this.addAll(registries, entries, key);
        }
    }

    private <T> void addAll(HolderLookup.Provider registries, Entries entries, ResourceKey<? extends Registry<?>> key) {
        @SuppressWarnings("unchecked")
        ResourceKey<? extends Registry<T>> typed = (ResourceKey<? extends Registry<T>>) key;
        if (key.equals(Registries.DENSITY_FUNCTION)) {
            @SuppressWarnings("unchecked")
            HolderLookup.RegistryLookup<DensityFunction> densityFunctions = (HolderLookup.RegistryLookup<DensityFunction>) (Object) registries.lookupOrThrow(typed);
            this.addDensityFunctions(registries, entries, densityFunctions);
            return;
        }
        entries.addAll(registries.lookupOrThrow(typed));
    }

    /**
     * DIAGNOSTIC: test-encodes each Aether density function and reports the ones that cannot be written
     * (e.g. a HolderHolder sitting in a spot whose codec only supports direct functions). Failing functions are
     * logged by name and skipped so the rest of datagen can continue. Fix every function named in the log, then
     * re-run: the generated pack is INCOMPLETE until nothing is reported here.
     */
    private void addDensityFunctions(HolderLookup.Provider registries, Entries entries, HolderLookup.RegistryLookup<DensityFunction> lookup) {
        RegistryOps<JsonElement> ops = registries.createSerializationContext(JsonOps.INSTANCE);
        lookup.listElements().forEach(reference -> {
            if (!reference.key().toString().contains("/ " + AetherII.MODID + ":")) {
                return;
            }
            try {
                var result = DensityFunction.DIRECT_CODEC.encodeStart(ops, reference.value());
                if (result.isError()) {
                    LOGGER.error("DENSITY FUNCTION CANNOT BE ENCODED (skipped): {} -> {}", reference.key(), result.error().get().message());
                    return;
                }
            } catch (Throwable throwable) {
                LOGGER.error("DENSITY FUNCTION CANNOT BE ENCODED (skipped): {} -> {}", reference.key(), throwable.toString());
                return;
            }
            entries.add(lookup, reference.key());
        });
    }

    @Override
    public String getName() {
        return "Registry Sets : " + AetherII.MODID;
    }
}