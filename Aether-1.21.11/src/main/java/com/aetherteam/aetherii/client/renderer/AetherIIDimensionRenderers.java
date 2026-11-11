package com.aetherteam.aetherii.client.renderer;

import org.joml.Vector4f;
import net.minecraft.util.ARGB;
import net.minecraft.client.multiplayer.ClientLevel;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.renderer.level.HolyIslesSkyboxRenderer;
import com.aetherteam.aetherii.client.renderer.level.HolyIslesWeatherEffectRenderer;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.context.ContextKey;

public class AetherIIDimensionRenderers {
    public static final RenderStateDataKey<Boolean> IS_HOLY_ISLES = RenderStateDataKey.create();
    public static final HolyIslesSkyboxRenderer SKY = new HolyIslesSkyboxRenderer();

    public static boolean isHolyIsles(ClientLevel level) {
        return level != null && level.dimensionTypeRegistration().is(AetherIIDimensions.AETHER_HOLY_ISLES_DIMENSION_TYPE);
    }
    public static final RenderStateDataKey<Float> DATA_THUNDER_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":thunder");
    public static final RenderStateDataKey<Float> DATA_TIME_OF_DAY_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":time_of_day");

    public static final Identifier AETHER_SKY_ID = Identifier.fromNamespaceAndPath(AetherII.MODID, "aether_sky");
    public static final Identifier AETHER_WEATHER_ID = Identifier.fromNamespaceAndPath(AetherII.MODID, "aether_weather");
    public static final Identifier AETHER_CLOUDS_ID = Identifier.fromNamespaceAndPath(AetherII.MODID, "aether_clouds");

    public static void registerDimensionEffect() {
        WorldRenderEvents.END_EXTRACTION.register(AetherIIDimensionRenderers::extractDimensionEffect);
    }

    public static void extractDimensionEffect(WorldExtractionContext event) {
        event.worldState().setData(IS_HOLY_ISLES, isHolyIsles(event.world()));
        if (event.world().dimensionTypeRegistration().is(AetherIIDimensions.AETHER_HOLY_ISLES_DIMENSION_TYPE)) {
            event.worldState().setData(DATA_THUNDER_KEY, event.world().getThunderLevel(Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false)));
            event.worldState().setData(DATA_TIME_OF_DAY_KEY, timeOfDay(event.world().getDayTime()));
            float time = timeOfDay(event.world().getDayTime());
            event.worldState().skyRenderState.sunriseAndSunsetColor = SKY.isSunriseOrSunset(time) ? SKY.getSunriseOrSunsetColor(time) : 0;
            event.worldState().skyRenderState.shouldRenderDarkDisc = false;
        }
    }

    public static int argb(org.joml.Vector3fc color) {
        return ARGB.colorFromFloat(1.0F, color.x(), color.y(), color.z());
    }

    private static Vector4f toRgba(int argb) {
        return new Vector4f(ARGB.redFloat(argb), ARGB.greenFloat(argb), ARGB.blueFloat(argb), ARGB.alphaFloat(argb));
    }

    public static float timeOfDay(long dayTime) {
        double d0 = Mth.frac((double) dayTime / (double) 24000.0F - (double) 0.25F);
        double d1 = (double) 0.5F - Math.cos(d0 * Math.PI) / (double) 2.0F;
        return (float) (d0 * (double) 2.0F + d1) / 3.0F;
    }
}
