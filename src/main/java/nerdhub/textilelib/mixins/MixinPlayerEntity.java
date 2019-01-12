package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.PlayerEvents;
import nerdhub.textilelib.events.TickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(Entity entity_1, Hand hand_1, CallbackInfoReturnable cir) {
        PlayerEvents.PlayerInteractEvent playerInteractEntity = new PlayerEvents.PlayerInteractEvent((PlayerEntity) (Object) this, ((PlayerEntity) (Object) this).getActiveHand(), entity_1);
        EventRegistry.INSTANCE.fireEvent(playerInteractEntity);

        if (playerInteractEntity.isCanceled()) {
            cir.setReturnValue(ActionResult.FAILURE);
            cir.cancel();
        }
    }

    @Inject(method = "update", at = @At("HEAD"))
    public void update(CallbackInfo ci) {
        TickEvents.PlayerTickEvent playerTickEvent = new TickEvents.PlayerTickEvent((PlayerEntity) (Object) this);
        EventRegistry.INSTANCE.fireEvent(playerTickEvent);
    }

    @Shadow
    public abstract boolean isSpectator();
}
