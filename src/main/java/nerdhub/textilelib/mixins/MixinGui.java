package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.render.GuiDrawEvent;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MixinGui {

    @Inject(method = "draw", at = @At("RETURN"))
    public void draw(int int_1, int int_2, float float_1, CallbackInfo ci) {
        GuiDrawEvent guiDrawEvent = new GuiDrawEvent((Gui) (Object) this, int_1, int_1, float_1);
        EventRegistry.INSTANCE.fireEvent(guiDrawEvent);
    }
}