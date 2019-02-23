package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.player.PlayerSwingEvent;
import nerdhub.textilelib.events.tick.ClientTickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow protected int attackCooldown;

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void doAttack(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        PlayerSwingEvent playerLeftClick = new PlayerSwingEvent(client.player);
        if(attackCooldown <= 0) {
            if(client.hitResult != null) {
                if(client.hitResult.getType() == HitResult.Type.NONE) {
                    EventRegistry.INSTANCE.fireEvent(playerLeftClick);
                    if(playerLeftClick.isCanceled()) {
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ClientTickEvent clientTickEvent = new ClientTickEvent();
        EventRegistry.INSTANCE.fireEvent(clientTickEvent);
    }
}
