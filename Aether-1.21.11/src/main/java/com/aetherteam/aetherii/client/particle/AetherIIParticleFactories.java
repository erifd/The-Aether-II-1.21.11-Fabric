package com.aetherteam.aetherii.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.block.fluid.AlkahestFluid;
import com.aetherteam.aetherii.client.AetherIIClient;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.material.Fluids;

public class AetherIIParticleFactories {
    /**
     * @see AetherIIClient#onInitializeClient() 
     */
    public static void registerParticleFactories() {
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.AETHER_PORTAL, AetherPortalParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SKYROOT_LEAVES, AetherLeafParticle.SkyrootFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SKYPLANE_LEAVES, AetherLeafParticle.SkyplaneFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SKYBIRCH_LEAVES, AetherLeafParticle.SkybirchFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SKYPINE_LEAVES, AetherLeafParticle.SkypineFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.WISPROOT_LEAVES, AetherLeafParticle.WisprootFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.WISPTOP_LEAVES, AetherLeafParticle.WisptopFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.GREATROOT_LEAVES, AetherLeafParticle.GreatrootFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.GREATOAK_LEAVES, AetherLeafParticle.GreatoakFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.GREATBOA_LEAVES, AetherLeafParticle.GreatboaFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.AMBEROOT_LEAVES, AetherLeafParticle.AmberootFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.IRRADIATED_LEAVES, AetherLeafParticle.AmberootFactory::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_WATER, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> new DripParticle.DripHangParticle(level, x, y, z, Fluids.WATER, AetherIIParticleTypes.FALLING_WATER, spriteSet.get(random)) {
            @Override
            public SingleQuadParticle.Layer getLayer() {
                return Layer.TRANSLUCENT;
            }
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.FALLING_WATER, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.WATER, AetherIIParticleTypes.SPLASH, spriteSet.get(random)) {
            @Override
            public SingleQuadParticle.Layer getLayer() {
                return Layer.TRANSLUCENT;
            }
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SPLASH, spriteSet -> (particle, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            SplashParticle splashParticle = new SplashParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.get(random)) {
                @Override
                public SingleQuadParticle.Layer getLayer() {
                    return Layer.TRANSLUCENT;
                }
            };
//            splashParticle.getSprite(spriteSet);
            return splashParticle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.GLASS_FEATHERS, GlassFeathersParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.AMBROSIUM, AmbrosiumParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.ALKAHEST, AlkahestParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.HESTVEIL, HestveilParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripHangParticle(level, x, y, z, AetherIIFluids.ALKAHEST, AetherIIParticleTypes.FALLING_ALKAHEST, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.FALLING_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.FallAndLandParticle(level, x, y, z, AetherIIFluids.ALKAHEST, ParticleTypes.WHITE_SMOKE, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_DRIPSTONE_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripHangParticle(level, x, y, z, AetherIIFluids.ALKAHEST, AetherIIParticleTypes.FALLING_DRIPSTONE_ALKAHEST, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.FALLING_DRIPSTONE_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripstoneFallAndLandParticle(level, x, y, z, AetherIIFluids.ALKAHEST, ParticleTypes.WHITE_SMOKE, spriteSet.get(random)) {
                @Override
                protected void postMoveUpdate() {
                    if (this.onGround) {
                        BlockPos pos = BlockPos.containing(this.x, this.y, this.z).below();
                        if (this.level.getBlockState(pos).isSolid() && !this.level.getBlockState(pos).is(AetherIITags.Blocks.ALKAHEST_RESISTANT)) {
                            AlkahestFluid.progressivelyDestroyBlock(this.level, pos, 3, true);
                        }
                    }
                    super.postMoveUpdate();
                }
            };
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.GRAVITY_DUST, GravityDustParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.RAIN, spriteSet -> (particle, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            WaterDropParticle rainParticle = new WaterDropParticle(level, x, y, z, spriteSet.get(random)) {
                @Override
                public SingleQuadParticle.Layer getLayer() {
                    return Layer.TRANSLUCENT;
                }
            };
//            rainParticle.pickSprite(spriteSet);
            return rainParticle;
        });
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.IRRADIATION, IrradiationParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SLASH_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.IMPACT_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.PIERCE_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SWEEP_ATTACK, BlueAttackSweepParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.SHOCK_ATTACK, YellowAttackShockParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.STAB_ATTACK, RedAttackStabParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.EFFECT_BUILDUP, SpellParticle.MobEffectProvider::new);

        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.ZEPHYR_SNOWFLAKE, SnowflakeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.TEMPEST_SMOKE, TempestSmokeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.MOA_HUNGRY, HeartParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.LOCKED_BLOCK, CopyBlockParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.BOSS_DOORWAY_BLOCK, CopyBlockParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AetherIIParticleTypes.TREASURE_DOORWAY_BLOCK, CopyBlockParticle.Provider::new);
    }
}
