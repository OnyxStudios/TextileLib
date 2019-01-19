package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.render.RenderWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow
    @Final
    private MinecraftClient client;

    //TODO Map to renderWorld
    @Inject(at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=entities", shift = At.Shift.BEFORE), method = "renderCenter")
     private void renderCenter(float float_1, long long_1, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_world");
        RenderWorldEvent renderWorldEvent = new RenderWorldEvent(this.client.worldRenderer, float_1);
        EventRegistry.INSTANCE.fireEvent(renderWorldEvent);
    }
}
