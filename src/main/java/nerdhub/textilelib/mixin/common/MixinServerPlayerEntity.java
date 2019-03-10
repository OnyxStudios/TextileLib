package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.entity.player.PlayerDeathCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        if(!PlayerDeathCallback.EVENT.invoker().onPlayerDeath(((ServerPlayerEntity) (Object) this).getServerWorld(), (ServerPlayerEntity) (Object) this, damageSource)) {
            ci.cancel();
        }
    }
}
