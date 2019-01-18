package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.player.PlayerInteractBlockEvent;

import net.minecraft.client.network.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class MixinClientPlayerInteractionManager {

    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void interactBlock(ClientPlayerEntity clientPlayerEntity_1, ClientWorld clientWorld_1, Hand hand_1, BlockHitResult blockHitResult_1, CallbackInfoReturnable<ActionResult> cir) {
        PlayerInteractBlockEvent interactBlockEvent = new PlayerInteractBlockEvent(clientPlayerEntity_1, hand_1, clientWorld_1.getBlockState(blockHitResult_1.getBlockPos()), blockHitResult_1.getBlockPos());
        EventRegistry.INSTANCE.fireEvent(interactBlockEvent);
        if(interactBlockEvent.isCanceled()) {
            cir.setReturnValue(ActionResult.FAILURE);
            cir.cancel();
        }
    }
}
