package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.TickEvent;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "update", at = @At("HEAD"))
    public void update(CallbackInfo ci) {
        TickEvent.EntityTickEvent entityTickEvent = new TickEvent.EntityTickEvent((Entity) (Object) this);
        EventRegistry.INSTANCE.fireEvent(entityTickEvent);

    }
}
