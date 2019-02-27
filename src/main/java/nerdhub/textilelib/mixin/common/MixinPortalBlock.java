package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.block.PortalCreationCallback;
import net.minecraft.block.PortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PortalBlock.class)
public abstract class MixinPortalBlock {

    @Inject(method = "method_10352", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PortalBlock$class_2424;method_10363()V", shift = At.Shift.BEFORE), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void createPortal(IWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, PortalBlock.class_2424 placeHandler) {
        if(PortalCreationCallback.EVENT.invoker().tryCreatePortal(world, pos, world.getBlockState(pos), placeHandler)) {
            cir.setReturnValue(false);
        }
    }
}