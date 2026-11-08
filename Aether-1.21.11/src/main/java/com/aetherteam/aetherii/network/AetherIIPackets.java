package com.aetherteam.aetherii.network;

import com.aetherteam.aetherii.network.packet.clientbound.RecipeSyncPacket;
import com.aetherteam.aetherii.network.packet.clientbound.ExtraSpawnDataPacket;
import com.aetherteam.aetherii.network.packet.clientbound.DataMapSyncPacket;
import com.aetherteam.aetherii.network.packet.clientbound.*;
import com.aetherteam.aetherii.network.packet.serverbound.*;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.function.Consumer;

public class AetherIIPackets {
    private static Consumer<CustomPacketPayload> clientSender = payload -> {};

    public static void setClientSender(Consumer<CustomPacketPayload> sender) {
        clientSender = sender;
    }

    public static void sendToServer(CustomPacketPayload payload) {
        clientSender.accept(payload);
    }

    public static void init() {
        // CLIENTBOUND PAYLOADS
        PayloadTypeRegistry.playS2C().register(AerbunnyMessagePacket.TYPE, AerbunnyMessagePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AlkahestDamageBlockPacket.TYPE, AlkahestDamageBlockPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AlkahestFizzPacket.TYPE, AlkahestFizzPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AlkahestItemSmokePacket.TYPE, AlkahestItemSmokePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AltarParticlesPacket.TYPE, AltarParticlesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AttackShockParticlePacket.TYPE, AttackShockParticlePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(AttackStabParticlePacket.TYPE, AttackStabParticlePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(BossInfoPacket.Display.TYPE, BossInfoPacket.Display.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(BossInfoPacket.Remove.TYPE, BossInfoPacket.Remove.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(BreakItemPacket.TYPE, BreakItemPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ClientGrabItemPacket.TYPE, ClientGrabItemPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(DamageTypeParticlePacket.TYPE, DamageTypeParticlePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(FlushGuidebookDataPacket.TYPE, FlushGuidebookDataPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ForgeSoundPacket.TYPE, ForgeSoundPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(FreezingParticlePacket.TYPE, FreezingParticlePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(GrassTintSyncPacket.TYPE, GrassTintSyncPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(DataMapSyncPacket.TYPE, DataMapSyncPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ExtraSpawnDataPacket.TYPE, ExtraSpawnDataPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(RecipeSyncPacket.TYPE, RecipeSyncPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(GuidebookToastPacket.TYPE, GuidebookToastPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(HestveilExplosionEffectsPacket.TYPE, HestveilExplosionEffectsPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(HourglassFinishParticlesPacket.TYPE, HourglassFinishParticlesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(HourglassProcessParticlesPacket.TYPE, HourglassProcessParticlesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(MusicBlockPlayPacket.TYPE, MusicBlockPlayPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(PortalTravelSoundPacket.TYPE, PortalTravelSoundPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(RemountAerbunnyPacket.TYPE, RemountAerbunnyPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ResistanceKnockbackPacket.TYPE, ResistanceKnockbackPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(SetAccessoriesPacket.TYPE, SetAccessoriesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(SetVehiclePacket.TYPE, SetVehiclePacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(SwetSyncPacket.TYPE, SwetSyncPacket.STREAM_CODEC);

        // SERVERBOUND PAYLOADS
        PayloadTypeRegistry.playC2S().register(AerbunnyPuffPacket.TYPE, AerbunnyPuffPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(AlkahestBreakBlockPacket.TYPE, AlkahestBreakBlockPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(CheckBestiaryEntryPacket.TYPE, CheckBestiaryEntryPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(CheckEffectsEntryPacket.TYPE, CheckEffectsEntryPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ClearAccessoriesPacket.TYPE, ClearAccessoriesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ClearItemPacket.TYPE, ClearItemPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(CurrencyAmountPacket.TYPE, CurrencyAmountPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(DiscardCompanionDeathPacket.TYPE, DiscardCompanionDeathPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(DiscardCompanionPacket.TYPE, DiscardCompanionPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ForgeRenamePacket.TYPE, ForgeRenamePacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ForgeSlotCharmsPacket.TYPE, ForgeSlotCharmsPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ForgeTriggerSoundPacket.TYPE, ForgeTriggerSoundPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(ForgeUpgradePacket.TYPE, ForgeUpgradePacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(MoaFlyModeChangePacket.TYPE, MoaFlyModeChangePacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(MountJumpedPacket.TYPE, MountJumpedPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(MovementDataPacket.TYPE, MovementDataPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(OpenGuidebookPacket.TYPE, OpenGuidebookPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(OpenInventoryPacket.TYPE, OpenInventoryPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(OutpostRespawnPacket.TYPE, OutpostRespawnPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(SkiffParticlesPacket.TYPE, SkiffParticlesPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(SkiffSteeringPacket.TYPE, SkiffSteeringPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(StoreCompanionItemEntityPacket.TYPE, StoreCompanionItemEntityPacket.STREAM_CODEC);

        // SERVER RECEIVERS
        ServerPlayNetworking.registerGlobalReceiver(AerbunnyPuffPacket.TYPE, (payload, context) -> AerbunnyPuffPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(AlkahestBreakBlockPacket.TYPE, (payload, context) -> AlkahestBreakBlockPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(CheckBestiaryEntryPacket.TYPE, (payload, context) -> CheckBestiaryEntryPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(CheckEffectsEntryPacket.TYPE, (payload, context) -> CheckEffectsEntryPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ClearAccessoriesPacket.TYPE, (payload, context) -> ClearAccessoriesPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ClearItemPacket.TYPE, (payload, context) -> ClearItemPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(CurrencyAmountPacket.TYPE, (payload, context) -> CurrencyAmountPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(DiscardCompanionDeathPacket.TYPE, (payload, context) -> DiscardCompanionDeathPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(DiscardCompanionPacket.TYPE, (payload, context) -> DiscardCompanionPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ForgeRenamePacket.TYPE, (payload, context) -> ForgeRenamePacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ForgeSlotCharmsPacket.TYPE, (payload, context) -> ForgeSlotCharmsPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ForgeTriggerSoundPacket.TYPE, (payload, context) -> ForgeTriggerSoundPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(ForgeUpgradePacket.TYPE, (payload, context) -> ForgeUpgradePacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(MoaFlyModeChangePacket.TYPE, (payload, context) -> MoaFlyModeChangePacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(MountJumpedPacket.TYPE, (payload, context) -> MountJumpedPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(MovementDataPacket.TYPE, (payload, context) -> MovementDataPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(OpenGuidebookPacket.TYPE, (payload, context) -> OpenGuidebookPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(OpenInventoryPacket.TYPE, (payload, context) -> OpenInventoryPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(OutpostRespawnPacket.TYPE, (payload, context) -> OutpostRespawnPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(SkiffParticlesPacket.TYPE, (payload, context) -> SkiffParticlesPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(SkiffSteeringPacket.TYPE, (payload, context) -> SkiffSteeringPacket.handleServer(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(StoreCompanionItemEntityPacket.TYPE, (payload, context) -> StoreCompanionItemEntityPacket.handleServer(payload, context.player()));
    }
}
