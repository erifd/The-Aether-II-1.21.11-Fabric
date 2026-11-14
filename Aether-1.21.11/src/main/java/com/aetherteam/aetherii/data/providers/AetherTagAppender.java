package com.aetherteam.aetherii.data.providers;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

/**
 * Wraps the vanilla {@link TagAppender} (which only takes {@link ResourceKey}s in 26.2) so tag data can keep adding
 * registry objects directly, like NeoForge's {@code TagAppender<T, E>} allowed.
 */
public class AetherTagAppender<T> {
    private final Sink<T> sink;
    private final Registry<T> registry;

    public AetherTagAppender(TagBuilder builder, Registry<T> registry) {
        this(Sink.of(builder), registry);
    }

    public AetherTagAppender(Sink<T> sink, Registry<T> registry) {
        this.sink = sink;
        this.registry = registry;
    }

    @SafeVarargs
    public final AetherTagAppender<T> add(T... elements) {
        for (T element : elements) {
            this.sink.add(this.registry.getResourceKey(element).orElseThrow(() -> new IllegalArgumentException("Unregistered tag element " + element)));
        }
        return this;
    }

    @SafeVarargs
    public final AetherTagAppender<T> addKeys(ResourceKey<T>... keys) {
        for (ResourceKey<T> key : keys) {
            this.sink.add(key);
        }
        return this;
    }

    public AetherTagAppender<T> addOptional(ResourceKey<T> key) {
        this.sink.addOptional(key);
        return this;
    }

    public AetherTagAppender<T> addTag(TagKey<T> tag) {
        this.sink.addTag(tag);
        return this;
    }

    @SafeVarargs
    public final AetherTagAppender<T> addTags(TagKey<T>... tags) {
        for (TagKey<T> tag : tags) {
            this.sink.addTag(tag);
        }
        return this;
    }

    public AetherTagAppender<T> addOptionalTag(TagKey<T> tag) {
        this.sink.addOptionalTag(tag);
        return this;
    }

    /**
     * The four operations a tag entry needs; lets one appender forward into another registry's tag
     * (block tags mirrored into item tags).
     */
    public interface Sink<T> {
        void add(ResourceKey<T> key);

        void addOptional(ResourceKey<T> key);

        void addTag(TagKey<T> tag);

        void addOptionalTag(TagKey<T> tag);

        static <T> Sink<T> of(TagBuilder builder) {
            return new Sink<>() {
                @Override
                public void add(ResourceKey<T> key) {
                    builder.addElement(key.identifier());
                }

                @Override
                public void addOptional(ResourceKey<T> key) {
                    builder.addOptionalElement(key.identifier());
                }

                @Override
                public void addTag(TagKey<T> tag) {
                    // Fabric's tag provider only validates references against tags defined in this datagen run, so
                    // #c:* / #minecraft:* references (supplied by Fabric, NeoForge-style conventions or vanilla at
                    // runtime) are written as optional. Only the mod's own tags stay required.
                    if (AetherII.MODID.equals(tag.location().getNamespace())) {
                        builder.addTag(tag.location());
                    } else {
                        builder.addOptionalTag(tag.location());
                    }
                }

                @Override
                public void addOptionalTag(TagKey<T> tag) {
                    builder.addOptionalTag(tag.location());
                }
            };
        }
    }

    public static <T> void setExternalTagLookup(Object provider) {
    }
}
