package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.player.PlayerInteractEntityEvent;
import nerdhub.textilelib.events.tick.PlayerTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void interact(Entity entity_1, Hand hand_1, CallbackInfoReturnable<ActionResult> cir) {
        PlayerInteractEntityEvent playerInteractEntity = new PlayerInteractEntityEvent((PlayerEntity) (Object) this, hand_1, entity_1);
        EventRegistry.INSTANCE.fireEvent(playerInteractEntity);
        if(playerInteractEntity.isCanceled()) {
            cir.setReturnValue(ActionResult.FAILURE);
            cir.cancel();
        }
    }

    @Inject(method = "update", at = @At("HEAD"))
    private void update(CallbackInfo ci) {
        PlayerTickEvent playerTickEvent = new PlayerTickEvent((PlayerEntity) (Object) this);
        EventRegistry.INSTANCE.fireEvent(playerTickEvent);
    }
}
