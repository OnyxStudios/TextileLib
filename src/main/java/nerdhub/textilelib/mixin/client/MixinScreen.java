package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.render.DrawScreenCallback;
import nerdhub.textilelib.event.client.render.tooltip.DrawStackTooltipCallback;
import net.minecraft.client.gui.Screen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(Screen.class)
public class MixinScreen {

    //TODO hook into GameRenderer#L628 (this.client.currentScreen.draw) instead
    @Inject(method = "draw", at = @At("RETURN"))
    private void draw(int mouseX, int mouseY, float deltaTime, CallbackInfo ci) {
        DrawScreenCallback.EVENT.invoker().drawScreen((Screen) (Object) this, mouseX, mouseY, deltaTime);
    }

    @Inject(method = "drawStackTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Screen;getStackTooltip(Lnet/minecraft/item/ItemStack;)Ljava/util/List;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void drawStackTooltip(ItemStack stack, int mouseX, int mouseY, CallbackInfo ci, List<String> tooltipList) {
        if(!DrawStackTooltipCallback.EVENT.invoker().drawTooltip(stack, tooltipList, mouseX, mouseY)) {
            ci.cancel();
        }
    }
}