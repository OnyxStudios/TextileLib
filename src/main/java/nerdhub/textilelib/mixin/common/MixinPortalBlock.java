package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.block.PortalCreationCallback;
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
    @Inject(method = "method_10352", at = @At("HEAD"), cancellable = true)
    private void method_10352(IWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(PortalCreationCallback.EVENT.invoker().tryCreatePortal(world, pos, world.getBlockState(pos))) {
            cir.setReturnValue(false);
        }
    }
}