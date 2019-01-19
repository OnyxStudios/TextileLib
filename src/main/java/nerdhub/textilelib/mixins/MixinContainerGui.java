package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.render.TooltipDrawEvent;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.container.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerGui.class)
public abstract class MixinContainerGui extends MixinGui {

    @Shadow
    protected Slot focusedSlot;

    @Inject(method = "drawMousoverTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ContainerGui;drawStackTooltip(Lnet/minecraft/item/ItemStack;II)V", shift = At.Shift.BEFORE), cancellable = true)
    private void drawMousoverTooltip(int int_1, int int_2, CallbackInfo ci) {
        TooltipDrawEvent tooltipDrawEvent = new TooltipDrawEvent(this.focusedSlot.getStack(), this.getStackTooltip(this.focusedSlot.getStack()), int_1, int_2);
        EventRegistry.INSTANCE.fireEvent(tooltipDrawEvent);

        if(tooltipDrawEvent.isCanceled()) {
            ci.cancel();
        }
    }
}
