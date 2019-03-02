package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.render.DrawScreenCallback;
import nerdhub.textilelib.event.client.render.tooltip.DrawStackTooltipCallback;
import net.minecraft.client.gui.Screen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public class MixinScreen {

    //TODO hook into GameRenderer#L628 (this.client.currentScreen.draw) instead
    @Inject(method = "draw", at = @At("RETURN"))
    private void draw(int mouseX, int mouseY, float deltaTime, CallbackInfo ci) {
        DrawScreenCallback.EVENT.invoker().drawScreen((Screen) (Object) this, mouseX, mouseY, deltaTime);
    }

    @Inject(method = "drawStackTooltip", at = @At("HEAD"))
    private void drawStackTooltip(ItemStack stack, int mouseX, int mouseY, CallbackInfo ci) {
        List<String> tooltipList = ((Screen) (Object) this).getStackTooltip(stack);
        if(DrawStackTooltipCallback.EVENT.invoker().drawTooltip(stack, tooltipList, mouseX, mouseY)) {
            ((Screen) (Object) this).drawTooltip(tooltipList, mouseX, mouseY);
        }

        //Always cancel so only our drawTooltip runs, so if anyone decides to modify the tooltip list it will reflect it
        ci.cancel();
    }
}