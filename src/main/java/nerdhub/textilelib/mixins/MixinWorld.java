package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.tick.WorldTickEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(World.class)
public abstract class MixinWorld {

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(BooleanSupplier booleanSupplier_1, CallbackInfo ci) {
        WorldTickEvent worldTickEvent = new WorldTickEvent((World) (Object) this);
        EventRegistry.INSTANCE.fireEvent(worldTickEvent);
    }
}
