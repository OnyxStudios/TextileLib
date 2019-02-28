package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.render.DrawScreenCallback;
import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {

    //TODO hook into GameRenderer#L628 (this.client.currentScreen.draw) instead
    @Inject(method = "draw", at = @At("RETURN"))
    private void draw(int mouseX, int mouseY, float deltaTime, CallbackInfo ci) {
        DrawScreenCallback.EVENT.invoker().drawScreen((Screen) (Object) this, mouseX, mouseY, deltaTime);
    }
}