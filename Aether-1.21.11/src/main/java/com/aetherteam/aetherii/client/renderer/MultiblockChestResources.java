package com.aetherteam.aetherii.client.renderer;

import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.function.Function;

public record MultiblockChestResources<T>(T single, T left, T right) {
    public <R> MultiblockChestResources<R> map(Function<T, R> mapper) {
        return new MultiblockChestResources<>(mapper.apply(this.single), mapper.apply(this.left), mapper.apply(this.right));
    }

    public T select(ChestType type) {
        return switch (type) {
            case SINGLE -> this.single;
            case LEFT -> this.left;
            case RIGHT -> this.right;
        };
    }
}
