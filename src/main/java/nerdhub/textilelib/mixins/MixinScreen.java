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
    private void draw(int int_1, int int_2, float float_1, CallbackInfo ci) {
        ScreenDrawEvent guiDrawEvent = new ScreenDrawEvent((Screen) (Object) this, int_1, int_1, float_1);
        EventRegistry.INSTANCE.fireEvent(guiDrawEvent);
    }
}