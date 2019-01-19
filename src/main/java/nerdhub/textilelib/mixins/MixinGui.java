package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.render.GuiDrawEvent;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Gui.class)
public abstract class MixinGui {

    @Inject(method = "draw", at = @At("RETURN"), cancellable = true)
    private void draw(int int_1, int int_2, float float_1, CallbackInfo ci) {
        GuiDrawEvent guiDrawEvent = new GuiDrawEvent((Gui) (Object) this, int_1, int_2, float_1);
        EventRegistry.INSTANCE.fireEvent(guiDrawEvent);

        if(guiDrawEvent.isCanceled()) {
            ci.cancel();
        }
    }

    @Shadow
    public abstract List<String> getStackTooltip(ItemStack itemStack_1);
}