package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.tick.EntityTickEvent;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "update", at = @At("HEAD"))
    private void update(CallbackInfo ci) {
        EntityTickEvent entityTickEvent = new EntityTickEvent((Entity) (Object) this);
        EventRegistry.INSTANCE.fireEvent(entityTickEvent);
    }
}
