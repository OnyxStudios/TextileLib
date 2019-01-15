package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.PlayerInteractBlockEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager {

    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    public void interactBlock(ClientPlayerEntity clientPlayerEntity_1, ClientWorld clientWorld_1, BlockPos blockPos_1, Direction direction_1, Vec3d vec3d_1, Hand hand_1, CallbackInfoReturnable cir) {
        PlayerInteractBlockEvent interactBlockEvent = new PlayerInteractBlockEvent(clientPlayerEntity_1, clientPlayerEntity_1.getActiveHand(), clientWorld_1.getBlockState(blockPos_1), blockPos_1);
        EventRegistry.INSTANCE.fireEvent(interactBlockEvent);

        if(interactBlockEvent.isCanceled()) {
            cir.setReturnValue(ActionResult.FAILURE);
            cir.cancel();
        }
    }
}
