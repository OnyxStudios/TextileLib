package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.TickEvents;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "update", at = @At("HEAD"))
    public void update() {
        TickEvents.EntityTickEvent entityTickEvent = new TickEvents.EntityTickEvent();
        EventRegistry.runEvent(entityTickEvent);
    }
}
