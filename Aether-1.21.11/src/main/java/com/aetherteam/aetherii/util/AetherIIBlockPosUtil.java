package com.aetherteam.aetherii.util;

import net.minecraft.core.BlockPos;

import java.util.Optional;
import java.util.function.Predicate;

public final class AetherIIBlockPosUtil {
    private AetherIIBlockPosUtil() {}

    /** {@code BlockPos#findClosestMatch} as it existed before 26.3: nearest position by Manhattan distance inside the given box. */
    public static Optional<BlockPos> findClosestMatch(BlockPos start, int horizontalSearchRadius, int verticalSearchRadius, Predicate<BlockPos> predicate) {
        return BlockPos.findClosestMatch(start, horizontalSearchRadius, verticalSearchRadius, predicate);
    }
}
