package com.aetherteam.aetherii.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class AetherIIKeyMappings {
    public final static KeyMapping ALLOW_DISMOUNTING_PASSENGER = new KeyMapping("key.aether_ii.allow_dismounting_passenger.desc", 225 /* left shift keysym, same value Options#keyShift uses */, new KeyMapping.Category(Identifier.fromNamespaceAndPath(AetherII.MODID, "general")));

    public static void registerKeyMappings() {
        KeyBindingHelper.registerKeyBinding(ALLOW_DISMOUNTING_PASSENGER);
    }
}
