package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.BlockEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager {

    @Shadow
    public World world;

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    private void destroyBlock(BlockPos blockPos_1, CallbackInfoReturnable cir) {
        BlockEvents.BlockBreakEvent blockBreakEvent = new BlockEvents.BlockBreakEvent(world, blockPos_1, world.getBlockState(blockPos_1), player);
        EventRegistry.INSTANCE.fireEvent(blockBreakEvent);

        if (blockBreakEvent.isCanceled()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
