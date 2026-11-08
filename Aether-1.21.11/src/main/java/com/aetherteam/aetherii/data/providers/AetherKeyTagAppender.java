package com.aetherteam.aetherii.data.providers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

/**
 * Tag appender for registries whose entries are referenced by {@link ResourceKey} (biomes, structures, damage types).
 */
public class AetherKeyTagAppender<T> {
    private final TagBuilder builder;

    public AetherKeyTagAppender(TagBuilder builder) {
        this.builder = builder;
    }

    @SafeVarargs
    public final AetherKeyTagAppender<T> add(ResourceKey<T>... keys) {
        for (ResourceKey<T> key : keys) {
            this.builder.addElement(key.identifier());
        }
        return this;
    }

    public AetherKeyTagAppender<T> addOptional(ResourceKey<T> key) {
        this.builder.addOptionalElement(key.identifier());
        return this;
    }

    public AetherKeyTagAppender<T> addTag(TagKey<T> tag) {
        this.builder.addTag(tag.location());
        return this;
    }

    @SafeVarargs
    public final AetherKeyTagAppender<T> addTags(TagKey<T>... tags) {
        for (TagKey<T> tag : tags) {
            this.builder.addTag(tag.location());
        }
        return this;
    }

    public AetherKeyTagAppender<T> addOptionalTag(TagKey<T> tag) {
        this.builder.addOptionalTag(tag.location());
        return this;
    }
}
