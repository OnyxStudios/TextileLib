package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.entity.player.PlayerTickCallback;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    @Inject(method = "tick", at = @At("HEAD"))
    private void update(CallbackInfo ci) {
        PlayerTickCallback.EVENT.invoker().tick((PlayerEntity) (Object) this);
    }
}
