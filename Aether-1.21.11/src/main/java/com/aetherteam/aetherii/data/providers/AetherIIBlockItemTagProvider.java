package com.aetherteam.aetherii.data.providers;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public abstract class AetherIIBlockItemTagProvider {
    public AetherIIBlockItemTagProvider() {
    }

    public void run() {
        this.tag(AetherIITags.Blocks.AETHER_DIRT, AetherIITags.Items.AETHER_DIRT).add(
                AetherIIBlocks.AETHER_GRASS_BLOCK,
                AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK,
                AetherIIBlocks.AETHER_DIRT,
                AetherIIBlocks.COARSE_AETHER_DIRT,
                AetherIIBlocks.MYCELIAL_AETHER_DIRT,
                AetherIIBlocks.FERROSITE_MUD
        );
        this.tag(AetherIITags.Blocks.HOLYSTONE, AetherIITags.Items.HOLYSTONE).add(
                AetherIIBlocks.HOLYSTONE,
                AetherIIBlocks.MOSSY_HOLYSTONE,
                AetherIIBlocks.IRRADIATED_HOLYSTONE
        );
        this.tag(AetherIITags.Blocks.HOLYSTONE, AetherIITags.Items.HOLYSTONE).add(
                AetherIIBlocks.HOLYSTONE,
                AetherIIBlocks.MOSSY_HOLYSTONE,
                AetherIIBlocks.IRRADIATED_HOLYSTONE
        );
        this.tag(AetherIITags.Blocks.FERROSITE, AetherIITags.Items.FERROSITE).add(
                AetherIIBlocks.FERROSITE,
                AetherIIBlocks.RUSTED_FERROSITE
        );
        this.tag(AetherIITags.Blocks.AERCLOUDS, AetherIITags.Items.AERCLOUDS).add(
                AetherIIBlocks.COLD_AERCLOUD,
                AetherIIBlocks.BLUE_AERCLOUD,
                AetherIIBlocks.GOLDEN_AERCLOUD,
                AetherIIBlocks.GREEN_AERCLOUD,
                AetherIIBlocks.PURPLE_AERCLOUD,
                AetherIIBlocks.STORM_AERCLOUD
        );
        this.tag(AetherIITags.Blocks.CLOUDWOOL, AetherIITags.Items.CLOUDWOOL).add(
                AetherIIBlocks.CLOUDWOOL,
                AetherIIBlocks.WHITE_CLOUDWOOL,
                AetherIIBlocks.ORANGE_CLOUDWOOL,
                AetherIIBlocks.MAGENTA_CLOUDWOOL,
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL,
                AetherIIBlocks.YELLOW_CLOUDWOOL,
                AetherIIBlocks.LIME_CLOUDWOOL,
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.GRAY_CLOUDWOOL,
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL,
                AetherIIBlocks.CYAN_CLOUDWOOL,
                AetherIIBlocks.PURPLE_CLOUDWOOL,
                AetherIIBlocks.BLUE_CLOUDWOOL,
                AetherIIBlocks.BROWN_CLOUDWOOL,
                AetherIIBlocks.GREEN_CLOUDWOOL,
                AetherIIBlocks.RED_CLOUDWOOL,
                AetherIIBlocks.BLACK_CLOUDWOOL
        );
        this.tag(AetherIITags.Blocks.SKYROOT_LOGS, AetherIITags.Items.SKYROOT_LOGS).add(
                AetherIIBlocks.SKYROOT_LOG,
                AetherIIBlocks.SKYROOT_WOOD,
                AetherIIBlocks.SKYROOT_TRUNK,
                AetherIIBlocks.STRIPPED_SKYROOT_LOG,
                AetherIIBlocks.STRIPPED_SKYROOT_WOOD,
                AetherIIBlocks.STRIPPED_SKYROOT_TRUNK
        );
        this.tag(AetherIITags.Blocks.GREATROOT_LOGS, AetherIITags.Items.GREATROOT_LOGS).add(
                AetherIIBlocks.GREATROOT_LOG,
                AetherIIBlocks.GREATROOT_WOOD,
                AetherIIBlocks.GREATROOT_TRUNK,
                AetherIIBlocks.STRIPPED_GREATROOT_LOG,
                AetherIIBlocks.STRIPPED_GREATROOT_WOOD,
                AetherIIBlocks.STRIPPED_GREATROOT_TRUNK
        );
        this.tag(AetherIITags.Blocks.WISPROOT_LOGS, AetherIITags.Items.WISPROOT_LOGS).add(
                AetherIIBlocks.WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE,
                AetherIIBlocks.WISPROOT_WOOD,
                AetherIIBlocks.WISPROOT_TRUNK,
                AetherIIBlocks.MOSSY_WISPROOT_WOOD,
                AetherIIBlocks.MOSSY_WISPROOT_TRUNK,
                AetherIIBlocks.STRIPPED_WISPROOT_LOG,
                AetherIIBlocks.STRIPPED_WISPROOT_WOOD,
                AetherIIBlocks.STRIPPED_WISPROOT_TRUNK
        );
        this.tag(AetherIITags.Blocks.AMBEROOT_LOGS, AetherIITags.Items.AMBEROOT_LOGS).add(
                AetherIIBlocks.AMBEROOT_LOG,
                AetherIIBlocks.AMBEROOT_WOOD,
                AetherIIBlocks.AMBEROOT_TRUNK,
                AetherIIBlocks.AMBEROOT_DEPOSIT,
                AetherIIBlocks.STRIPPED_AMBEROOT_LOG,
                AetherIIBlocks.STRIPPED_AMBEROOT_WOOD,
                AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK
        );
        this.tag(AetherIITags.Blocks.GUARDIAN_LOGS, AetherIITags.Items.GUARDIAN_LOGS).add(
                AetherIIBlocks.GUARDIAN_LOG,
                AetherIIBlocks.GUARDIAN_WOOD,
                AetherIIBlocks.STRIPPED_GUARDIAN_LOG,
                AetherIIBlocks.STRIPPED_GUARDIAN_WOOD,
                AetherIIBlocks.INFECTED_LOG,
                AetherIIBlocks.INFECTED_WOOD,
                AetherIIBlocks.STRIPPED_INFECTED_LOG,
                AetherIIBlocks.STRIPPED_INFECTED_WOOD
        );

        this.tag(AetherIITags.Blocks.SKYROOT_DECORATIVE_BLOCKS, AetherIITags.Items.SKYROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FLOORBOARDS,
                AetherIIBlocks.SKYROOT_HIGHLIGHT,
                AetherIIBlocks.SKYROOT_SHINGLES,
                AetherIIBlocks.SKYROOT_SMALL_SHINGLES,
                AetherIIBlocks.SKYROOT_BASE_PLANKS,
                AetherIIBlocks.SKYROOT_TOP_PLANKS,
                AetherIIBlocks.SKYROOT_BASE_BEAM,
                AetherIIBlocks.SKYROOT_TOP_BEAM,
                AetherIIBlocks.SKYROOT_BEAM
        );
        this.tag(AetherIITags.Blocks.GREATROOT_DECORATIVE_BLOCKS, AetherIITags.Items.GREATROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.GREATROOT_FLOORBOARDS,
                AetherIIBlocks.GREATROOT_HIGHLIGHT,
                AetherIIBlocks.GREATROOT_SHINGLES,
                AetherIIBlocks.GREATROOT_SMALL_SHINGLES,
                AetherIIBlocks.GREATROOT_BASE_PLANKS,
                AetherIIBlocks.GREATROOT_TOP_PLANKS,
                AetherIIBlocks.GREATROOT_BASE_BEAM,
                AetherIIBlocks.GREATROOT_TOP_BEAM,
                AetherIIBlocks.GREATROOT_BEAM
        );
        this.tag(AetherIITags.Blocks.WISPROOT_DECORATIVE_BLOCKS, AetherIITags.Items.WISPROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.WISPROOT_FLOORBOARDS,
                AetherIIBlocks.WISPROOT_HIGHLIGHT,
                AetherIIBlocks.WISPROOT_SHINGLES,
                AetherIIBlocks.WISPROOT_SMALL_SHINGLES,
                AetherIIBlocks.WISPROOT_BASE_PLANKS,
                AetherIIBlocks.WISPROOT_TOP_PLANKS,
                AetherIIBlocks.WISPROOT_BASE_BEAM,
                AetherIIBlocks.WISPROOT_TOP_BEAM,
                AetherIIBlocks.WISPROOT_BEAM
        );
        this.tag(AetherIITags.Blocks.AMBEROOT_DECORATIVE_BLOCKS, AetherIITags.Items.AMBEROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.AMBEROOT_FLOORBOARDS,
                AetherIIBlocks.AMBEROOT_HIGHLIGHT,
                AetherIIBlocks.AMBEROOT_SHINGLES,
                AetherIIBlocks.AMBEROOT_SMALL_SHINGLES,
                AetherIIBlocks.AMBEROOT_BASE_PLANKS,
                AetherIIBlocks.AMBEROOT_TOP_PLANKS,
                AetherIIBlocks.AMBEROOT_BASE_BEAM,
                AetherIIBlocks.AMBEROOT_TOP_BEAM,
                AetherIIBlocks.AMBEROOT_BEAM
        );
        this.tag(AetherIITags.Blocks.HOLYSTONE_DECORATIVE_BLOCKS, AetherIITags.Items.HOLYSTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.HOLYSTONE_FLAGSTONES,
                AetherIIBlocks.HOLYSTONE_HEADSTONE,
                AetherIIBlocks.HOLYSTONE_KEYSTONE,
                AetherIIBlocks.HOLYSTONE_BASE_BRICKS,
                AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.HOLYSTONE_BASE_PILLAR,
                AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.HOLYSTONE_PILLAR
        );
        this.tag(AetherIITags.Blocks.FADED_HOLYSTONE_DECORATIVE_BLOCKS, AetherIITags.Items.FADED_HOLYSTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES,
                AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE,
                AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE,
                AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS,
                AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR,
                AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.FADED_HOLYSTONE_PILLAR
        );
        this.tag(AetherIITags.Blocks.UNDERSHALE_DECORATIVE_BLOCKS, AetherIITags.Items.UNDERSHALE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.UNDERSHALE_FLAGSTONES,
                AetherIIBlocks.UNDERSHALE_TILE,
                AetherIIBlocks.UNDERSHALE_BASE_BRICKS,
                AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS,
                AetherIIBlocks.UNDERSHALE_BASE_PILLAR,
                AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR,
                AetherIIBlocks.UNDERSHALE_PILLAR
        );
        this.tag(AetherIITags.Blocks.SENTRY_DECORATIVE_BLOCKS, AetherIITags.Items.SENTRY_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SENTRY_LIGHTSTONE,
                AetherIIBlocks.SENTRY_FLAGSTONES,
                AetherIIBlocks.SENTRY_TILE,
                AetherIIBlocks.SENTRY_BASE_BRICKS,
                AetherIIBlocks.SENTRY_CAPSTONE_BRICKS,
                AetherIIBlocks.SENTRY_BASE_PILLAR,
                AetherIIBlocks.SENTRY_CAPSTONE_PILLAR,
                AetherIIBlocks.SENTRY_PILLAR
        );
        this.tag(AetherIITags.Blocks.ICHORITE_DECORATIVE_BLOCKS, AetherIITags.Items.ICHORITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.ICHORITE_FLAGSTONES,
                AetherIIBlocks.ICHORITE_RUNESTONE,
                AetherIIBlocks.ICHORITE_KEYSTONE,
                AetherIIBlocks.ICHORITE_BASE_BRICKS,
                AetherIIBlocks.ICHORITE_CAPSTONE_BRICKS,
                AetherIIBlocks.ICHORITE_BASE_PILLAR,
                AetherIIBlocks.ICHORITE_CAPSTONE_PILLAR,
                AetherIIBlocks.ICHORITE_PILLAR
        );
        this.tag(AetherIITags.Blocks.MARBLED_ICHORITE_DECORATIVE_BLOCKS, AetherIITags.Items.MARBLED_ICHORITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.MARBLED_FLAGSTONES,
                AetherIIBlocks.MARBLED_KEYSTONE,
                AetherIIBlocks.MARBLED_BASE_BRICKS,
                AetherIIBlocks.MARBLED_CAPSTONE_BRICKS,
                AetherIIBlocks.MARBLED_BASE_PILLAR,
                AetherIIBlocks.MARBLED_CAPSTONE_PILLAR,
                AetherIIBlocks.MARBLED_PILLAR
        );
        this.tag(AetherIITags.Blocks.AGIOSITE_DECORATIVE_BLOCKS, AetherIITags.Items.AGIOSITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.AGIOSITE_FLAGSTONES,
                AetherIIBlocks.AGIOSITE_KEYSTONE,
                AetherIIBlocks.AGIOSITE_BASE_BRICKS,
                AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS,
                AetherIIBlocks.AGIOSITE_BASE_PILLAR,
                AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR,
                AetherIIBlocks.AGIOSITE_PILLAR
        );
        this.tag(AetherIITags.Blocks.ICESTONE_DECORATIVE_BLOCKS, AetherIITags.Items.ICESTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.ICESTONE_FLAGSTONES,
                AetherIIBlocks.ICESTONE_KEYSTONE,
                AetherIIBlocks.ICESTONE_BASE_BRICKS,
                AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.ICESTONE_BASE_PILLAR,
                AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.ICESTONE_PILLAR
        );
        this.tag(AetherIITags.Blocks.QUICKSOIL_GLASS_DECORATIVE_BLOCKS, AetherIITags.Items.QUICKSOIL_GLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.TILED_QUICKSOIL_GLASS,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS
        );
        this.tag(AetherIITags.Blocks.QUICKSOIL_GLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.QUICKSOIL_GLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE
        );
        this.tag(AetherIITags.Blocks.CRUDE_SCATTERGLASS_DECORATIVE_BLOCKS, AetherIITags.Items.CRUDE_SCATTERGLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS
        );
        this.tag(AetherIITags.Blocks.CRUDE_SCATTERGLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.CRUDE_SCATTERGLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE
        );
        this.tag(AetherIITags.Blocks.SCATTERGLASS_DECORATIVE_BLOCKS, AetherIITags.Items.SCATTERGLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS
        );
        this.tag(AetherIITags.Blocks.SCATTERGLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.SCATTERGLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE
        );
        this.tag(AetherIITags.Blocks.ARKENIUM_BARS_DECORATIVE_BLOCKS, AetherIITags.Items.ARKENIUM_BARS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.FLORAL_ARKENIUM_BARS,
                AetherIIBlocks.PATTERNED_ARKENIUM_BARS,
                AetherIIBlocks.CURVED_ARKENIUM_BARS
        );
        this.tag(AetherIITags.Blocks.ARILUM_LANTERN, AetherIITags.Items.ARILUM_LANTERN).add(
                AetherIIBlocks.WHITE_ARILUM_LANTERN,
                AetherIIBlocks.ORANGE_ARILUM_LANTERN,
                AetherIIBlocks.MAGENTA_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN,
                AetherIIBlocks.YELLOW_ARILUM_LANTERN,
                AetherIIBlocks.LIME_ARILUM_LANTERN,
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.GRAY_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN,
                AetherIIBlocks.CYAN_ARILUM_LANTERN,
                AetherIIBlocks.PURPLE_ARILUM_LANTERN,
                AetherIIBlocks.BLUE_ARILUM_LANTERN,
                AetherIIBlocks.BROWN_ARILUM_LANTERN,
                AetherIIBlocks.GREEN_ARILUM_LANTERN,
                AetherIIBlocks.RED_ARILUM_LANTERN,
                AetherIIBlocks.BLACK_ARILUM_LANTERN
        );
    }

    protected abstract TagAppender<Block, Block> tag(TagKey<Block> blockKey, TagKey<Item> itemKey);
}