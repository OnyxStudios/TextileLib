package nerdhub.textilelib.mixin.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import nerdhub.textilelib.event.entity.EntityDeathCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        System.out.println("Sending death event!");
        if (!EntityDeathCallback.EVENT.invoker().onEntityDeath(((LivingEntity) (Object) this).getEntityWorld(), (LivingEntity) (Object) this, damageSource)) {
            System.out.println("Cancelling event!");
            ci.cancel();
        }
    }
}
