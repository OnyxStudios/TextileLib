package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.player.PlayerSwingCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow public ClientPlayerEntity player;
    @Shadow protected int attackCooldown;

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void doAttack(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if(this.attackCooldown <= 0 && client.hitResult != null && client.hitResult.getType() == HitResult.Type.NONE) {
            if(PlayerSwingCallback.EVENT.invoker().onPlayerSwing(player)) {
                ci.cancel();
            }
        }
    }
}
