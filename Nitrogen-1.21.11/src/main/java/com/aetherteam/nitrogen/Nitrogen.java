package com.aetherteam.nitrogen;

import com.aetherteam.nitrogen.api.users.User;
import com.aetherteam.nitrogen.api.users.UserData;
import com.aetherteam.nitrogen.network.packet.clientbound.UpdateUserInfoPacket;
import com.aetherteam.nitrogen.network.packet.serverbound.TriggerUpdateInfoPacket;
import com.aetherteam.nitrogen.loot.modifiers.LootModifierManager;
import com.aetherteam.nitrogen.loot.modifiers.NitrogenLootModifiers;
import com.aetherteam.nitrogen.world.biomemodifier.BiomeModifierManager;
import com.aetherteam.nitrogen.world.biomemodifier.NitrogenBiomeModifierSerializers;
import com.aetherteam.nitrogen.world.foliageplacer.NitrogenFoliagePlacerTypes;
import com.aetherteam.nitrogen.world.trunkplacer.NitrogenTrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class Nitrogen implements ModInitializer {
    public static final String MODID = "nitrogen_internals";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        registerPackets();

        NitrogenFoliagePlacerTypes.init();
        NitrogenTrunkPlacerTypes.init();
        NitrogenLootModifiers.init();
        LootModifierManager.register();
        NitrogenBiomeModifierSerializers.init();
        BiomeModifierManager.register();

        ServerLifecycleEvents.SERVER_STARTED.register(UserData.Server::initializeFromCache);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> playerLoggedIn(handler.player));
    }

    private void registerPackets() {
        PayloadTypeRegistry.clientboundPlay().register(UpdateUserInfoPacket.TYPE, UpdateUserInfoPacket.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(TriggerUpdateInfoPacket.TYPE, TriggerUpdateInfoPacket.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(TriggerUpdateInfoPacket.TYPE, TriggerUpdateInfoPacket::execute);
    }

    public static void playerLoggedIn(ServerPlayer serverPlayer) {
        UUID uuid = serverPlayer.getGameProfile().id();
        Map<UUID, User> userData = UserData.Server.getStoredUsers();
        User user;
        if (userData.containsKey(uuid)) {
            user = userData.get(uuid);
            if (user != null && user.getRenewalDate() != null && isAfterRenewalTime(user)) {
                UserData.Server.sendUserRequest(serverPlayer.level().getServer(), serverPlayer, uuid);
            } else {
                if (ServerPlayNetworking.canSend(serverPlayer, UpdateUserInfoPacket.TYPE)) {
                    ServerPlayNetworking.send(serverPlayer, new UpdateUserInfoPacket(user));
                }
            }
        } else {
            UserData.Server.sendUserRequest(serverPlayer.level().getServer(), serverPlayer, uuid);
        }
    }

    private static boolean isAfterRenewalTime(User user) {
        ZonedDateTime renewalDateTime = LocalDateTime.parse(user.getRenewalDate(), User.DATE_FORMAT).atZone(ZoneId.of("UTC"));
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        return currentDateTime.isAfter(renewalDateTime);
    }
}
