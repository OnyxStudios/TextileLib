package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.entity.EntityTickCallback;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "tick", at = @At("HEAD"))
    private void update(CallbackInfo ci) {
        EntityTickCallback.EVENT.invoker().tick((Entity) (Object) this);
    }
}
