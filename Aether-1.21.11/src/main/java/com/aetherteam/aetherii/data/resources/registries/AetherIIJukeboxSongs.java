package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

import java.util.LinkedHashMap;
import java.util.Map;

public class AetherIIJukeboxSongs {
    public static ResourceKey<JukeboxSong> ASCENDING_DAWN = create("ascending_dawn");
    public static ResourceKey<JukeboxSong> AERWHALE = create("aerwhale");
    public static ResourceKey<JukeboxSong> APPROACHES = create("approaches");
    public static ResourceKey<JukeboxSong> DEMISE = create("demise");
    public static ResourceKey<JukeboxSong> CHINCHILLA = create("chinchilla");
    public static ResourceKey<JukeboxSong> HIGH = create("high");
    public static ResourceKey<JukeboxSong> REVOLUTIONS = create("revolutions");

    private static ResourceKey<JukeboxSong> create(String pName) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(AetherII.MODID, pName));
    }

    private static final Map<ResourceKey<JukeboxSong>, JukeboxSong> SONGS = new LinkedHashMap<>();

    static {
        define(ASCENDING_DAWN, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_ASCENDING_DAWN), 350, 2);
        define(AERWHALE, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_AERWHALE), 178, 3);
        define(APPROACHES, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_APPROACHES), 274, 4);
        define(DEMISE, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_DEMISE), 300, 5);
        define(CHINCHILLA, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_CHINCHILLA), 163, 6);
        define(HIGH, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_HIGH), 186, 7);
        define(REVOLUTIONS, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ITEM_ENGRAVED_DISC_REVOLUTIONS), 221, 8);
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        SONGS.forEach(context::register);
    }

    /** A directly-defined (non-registry-reference) song, used for item components that are built before datapack registries exist. */
    public static Holder<JukeboxSong> direct(ResourceKey<JukeboxSong> key) {
        return Holder.direct(SONGS.get(key));
    }

    private static void define(ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        SONGS.put(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), (float) lengthInSeconds, comparatorOutput));
    }
}