package com.aetherteam.aetherii.client.renderer.item.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.resources.Identifier;

import java.util.List;

/**
 * 1.21.11 port: the 26.x runtime-composed shield quads are not available, so this renders the parent model only.
 * Texture data is kept so datagen output stays stable.
 */
public class ShieldModel {
    public record Textures(List<Identifier> front, List<Identifier> back, Identifier handle, Identifier particle) {
        public static final Codec<ShieldModel.Textures> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.listOf(4, 4).fieldOf("front").forGetter(ShieldModel.Textures::front),
                Identifier.CODEC.listOf(4, 4).fieldOf("back").forGetter(ShieldModel.Textures::back),
                Identifier.CODEC.fieldOf("handle").forGetter(ShieldModel.Textures::handle),
                Identifier.CODEC.fieldOf("particle").forGetter(ShieldModel.Textures::particle)
        ).apply(instance, ShieldModel.Textures::new));
    }

    public record Unbaked(Identifier parent, ShieldModel.Textures textures) implements ItemModel.Unbaked {
        public static final MapCodec<ShieldModel.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("parent").forGetter(ShieldModel.Unbaked::parent),
                ShieldModel.Textures.CODEC.fieldOf("textures").forGetter(ShieldModel.Unbaked::textures)
        ).apply(instance, ShieldModel.Unbaked::new));

        @Override
        public MapCodec<? extends ItemModel.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context) {
            return new BlockModelWrapper.Unbaked(this.parent(), List.of()).bake(context);
        }

        @Override
        public void resolveDependencies(Resolver resolver) {
            resolver.markDependency(this.parent());
        }
    }
}
