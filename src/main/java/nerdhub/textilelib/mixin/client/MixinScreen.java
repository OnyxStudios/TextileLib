package nerdhub.textilelib.mixin.client;

import java.util.List;

import net.minecraft.client.gui.Screen;
import net.minecraft.item.ItemStack;

import nerdhub.textilelib.event.client.render.tooltip.DrawStackTooltipCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Screen.class)
public class MixinScreen {

    @Inject(method = "drawStackTooltip", at = @At("HEAD"), cancellable = true)
    private void drawStackTooltip(ItemStack stack, int mouseX, int mouseY, CallbackInfo ci) {
        List<String> tooltipList = ((Screen) (Object) this).getStackTooltip(stack);
        if (DrawStackTooltipCallback.EVENT.invoker().drawTooltip(stack, tooltipList, mouseX, mouseY)) {
            ((Screen) (Object) this).drawTooltip(tooltipList, mouseX, mouseY);
        }

        ci.cancel();
    }
}