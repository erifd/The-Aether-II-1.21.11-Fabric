package com.aetherteam.nitrogen.client;

import com.aetherteam.nitrogen.client.renderer.BlockStateRenderer;
import com.aetherteam.nitrogen.client.renderer.FluidStateRenderer;
import com.aetherteam.nitrogen.event.listeners.TooltipListeners;
import com.aetherteam.nitrogen.network.packet.clientbound.UpdateUserInfoPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.SpecialGuiElementRegistry;

public class NitrogenClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(UpdateUserInfoPacket.TYPE, UpdateUserInfoPacket::execute);
        TooltipListeners.init();

        SpecialGuiElementRegistry.register(context -> new BlockStateRenderer(context.vertexConsumers()));
        SpecialGuiElementRegistry.register(context -> new FluidStateRenderer(context.vertexConsumers()));
    }
}
