package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.block.SpawnPortalEvent;
import net.minecraft.block.PortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalBlock.class)
public abstract class MixinPortalBlock {

    //FIXME this does technically work, but fires way too often
    //TODO map this to attemptPortalSpawn
    @Inject(method = "method_10352", at = @At("HEAD"), cancellable = true)
    private void method_10352(IWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        SpawnPortalEvent spawnPortalEvent = new SpawnPortalEvent(world.getWorld(), pos, world.getBlockState(pos));
        EventRegistry.INSTANCE.fireEvent(spawnPortalEvent);
        if(spawnPortalEvent.isCanceled()) {
            cir.setReturnValue(false);
        }
    }
}