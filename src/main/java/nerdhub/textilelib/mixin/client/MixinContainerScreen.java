package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.render.tooltip.HoverTooltipDrawCallback;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.container.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ContainerScreen.class)
public class MixinContainerScreen {

    @Shadow
    protected Slot focusedSlot;

    @Inject(method = "drawMouseoverTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ContainerGui;drawStackTooltip(Lnet/minecraft/item/ItemStack;II)V", shift = At.Shift.BEFORE), cancellable = true)
    private void drawMouseoverTooltip(int x, int y, CallbackInfo ci) {
        List<String> tooltipList = ((ContainerScreen) (Object) this).getStackTooltip(this.focusedSlot.getStack());
        if(!HoverTooltipDrawCallback.EVENT.invoker().drawTooltip(this.focusedSlot.getStack(), tooltipList, x, y)) {
            ci.cancel();
        }
    }
}
