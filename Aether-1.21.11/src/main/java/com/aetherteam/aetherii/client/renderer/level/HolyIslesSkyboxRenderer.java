package com.aetherteam.aetherii.client.renderer.level;

import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;

/**
 * 1.21.11 port: the cloud cover disc relied on the 26.x render-pass API and is not ported yet; only the sunrise/sunset
 * helpers used by the dimension fog listener remain.
 */
public class HolyIslesSkyboxRenderer {
    public boolean isSunriseOrSunset(float timeOfDay) {
        float f = Mth.cos(timeOfDay * Mth.TWO_PI);
        return f >= -0.4F && f <= 0.4F;
    }

    public int getSunriseOrSunsetColor(float timeOfDay) {
        float f = Mth.cos(timeOfDay * Mth.TWO_PI);
        float f1 = f / 0.4F * 0.5F + 0.5F;
        float f2 = Mth.square(1.0F - (1.0F - Mth.sin(f1 * Mth.PI)) * 0.99F);
        return ARGB.colorFromFloat(f2, f1 * 0.3F + 0.65F, f1 * f1 * 0.7F + 0.25F, 0.4F);
    }
}
