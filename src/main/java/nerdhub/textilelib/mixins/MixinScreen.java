package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.render.ScreenDrawEvent;
import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {

    @Inject(method = "draw", at = @At("RETURN"))
    private void draw(int mouseX, int mouseY, float deltaTime, CallbackInfo ci) {
        ScreenDrawEvent guiDrawEvent = new ScreenDrawEvent((Screen) (Object) this, mouseX, mouseY, deltaTime);
        EventRegistry.INSTANCE.fireEvent(guiDrawEvent);
    }
}