package com.aetherteam.aetherii.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;
import com.aetherteam.aetherii.AetherIITags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class AetherIIBlockBuilders {
    public static Supplier<BlockBehaviour.Properties> coldAercloudProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.FLUTE)
                .strength(0.3F)
                .sound(SoundType.WOOL)
                .noOcclusion()
                .dynamicShape()
                .forceSolidOn()
                .isValidSpawn((state, level, pos, entityType) -> entityType.builtInRegistryHolder().is(AetherIITags.EntityType.SPAWNING_AERCLOUDS))
                .isRedstoneConductor(AetherIIBlockBuilders::never)
                .isSuffocating(AetherIIBlockBuilders::never)
                .isViewBlocking(AetherIIBlockBuilders::never);
    }

    public static Supplier<BlockBehaviour.Properties> specialAercloudProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.FLUTE)
                .strength(0.9F)
                .sound(SoundType.WOOL)
                .noOcclusion()
                .dynamicShape()
                .forceSolidOn()
                .isValidSpawn((state, level, pos, entityType) -> entityType.builtInRegistryHolder().is(AetherIITags.EntityType.SPAWNING_AERCLOUDS))
                .isRedstoneConductor(AetherIIBlockBuilders::never)
                .isSuffocating(AetherIIBlockBuilders::never)
                .isViewBlocking(AetherIIBlockBuilders::never)
                .requiresCorrectToolForDrops();
    }

    public static Supplier<BlockBehaviour.Properties> logProperties(MapColor topMapColor, MapColor sideMapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(block -> block.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    public static Supplier<BlockBehaviour.Properties> trunkProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava()
                .dynamicShape(); // shape depends on neighbors; Fabric builds the state shape cache at registration (tags not bound yet)
    }

    @SuppressWarnings("deprecation")
    public static Supplier<BlockBehaviour.Properties> leafPileProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .forceSolidOff()
                .isSuffocating(AetherIIBlockBuilders::never)
                .isViewBlocking(AetherIIBlockBuilders::never)
                .isRedstoneConductor(AetherIIBlockBuilders::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);
    }

    public static Supplier<BlockBehaviour.Properties> leavesProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .isValidSpawn(AetherIIBlockBuilders::spawnOnLeaves)
                .isSuffocating(AetherIIBlockBuilders::never)
                .isViewBlocking(AetherIIBlockBuilders::never)
                .isRedstoneConductor(AetherIIBlockBuilders::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);
    }

    public static Supplier<BlockBehaviour.Properties> arilumLanternProperties(MapColor mapColor) {
        return () -> BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .friction(0.8F)
                .noOcclusion()
                .sound(SoundType.FROGLIGHT)
                .lightLevel((state) -> 8);
    }

    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos, AABB nearPlaneBox) {
        return true;
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos, AABB nearPlaneBox) {
        return false;
    }

    public static BlockPos postProcessSelf(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return blockPos;
    }

    public static <A> boolean never(BlockState state, BlockGetter getter, BlockPos pos, A block) {
        return false;
    }

    public static boolean spawnOnLeaves(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType) {
        return entityType.builtInRegistryHolder().is(AetherIITags.EntityType.SPAWNING_LEAVES) || (state.getOptionalValue(AetherIIBlockStateProperties.MOSSY).isPresent() && state.getValue(AetherIIBlockStateProperties.MOSSY) != AetherIIBlockStateProperties.Mossy.NONE);
    }

    public static int lightLevel6(BlockState state) {
        return state.hasProperty(BlockStateProperties.LIT) && !state.getValue(BlockStateProperties.LIT) ? 0 : 6;
    }

    public static int lightLevel8(BlockState state) {
        return state.hasProperty(BlockStateProperties.LIT) && !state.getValue(BlockStateProperties.LIT) ? 0 : 8;
    }

    public static int lightLevel11(BlockState state) {
        return state.hasProperty(BlockStateProperties.LIT) && !state.getValue(BlockStateProperties.LIT) ? 0 : 11;
    }
}