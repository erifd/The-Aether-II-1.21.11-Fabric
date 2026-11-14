package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.event.AetherIIEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Orientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * {@code BlockEvent.NeighborNotifyEvent} on the server.
 * <p>
 * {@link LevelMixin} hooks Level#updateNeighborsAt, but in 1.21.11 the server-side work happens in ServerLevel's own
 * implementation, so that hook may never run for server levels (which left the Aether portal unlit). This repeats the
 * hook on ServerLevel. It is optional (require = 0): if ServerLevel does not declare the method it is skipped instead
 * of failing, and a double call is harmless because the portal shape search is idempotent.
 */
@Mixin(ServerLevel.class)
public abstract class ServerLevelNeighborNotifyMixin {
    @Inject(method = "updateNeighborsAt(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;)V", at = @At("HEAD"), cancellable = true, require = 0)
    private void aether_ii$serverNeighborNotify(BlockPos pos, Block sourceBlock, Orientation orientation, CallbackInfo ci) {
        ServerLevel self = (ServerLevel) (Object) this;
        if (!AetherIIEvents.NEIGHBOR_NOTIFY.invoker().onNeighborNotify(self, pos)) {
            ci.cancel();
        }
    }
}
