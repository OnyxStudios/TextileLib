package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.RenderWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "method_3178", at = @At("HEAD"))
    private void method_3178(float float_1, long long_1, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_world");
        RenderWorldEvent renderWorldEvent = new RenderWorldEvent(this.client.renderer, float_1);
        EventRegistry.fireEvent(renderWorldEvent);
    }
}
