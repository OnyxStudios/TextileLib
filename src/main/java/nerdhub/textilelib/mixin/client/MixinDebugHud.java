package nerdhub.textilelib.mixin.client;

import java.util.List;

import net.minecraft.client.gui.hud.DebugHud;

import nerdhub.textilelib.event.client.render.hud.DebugHudTextCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DebugHud.class)
public abstract class MixinDebugHud {

    @Inject(method = "getLeftText", at = @At("RETURN"))
    private void getLeftText(CallbackInfoReturnable<List<String>> cir) {
        DebugHudTextCallback.EVENT.invoker().getText(cir.getReturnValue(), DebugHudTextCallback.Side.LEFT);
    }

    @Inject(method = "getRightText", at = @At("RETURN"))
    private void getRightText(CallbackInfoReturnable<List<String>> cir) {
        DebugHudTextCallback.EVENT.invoker().getText(cir.getReturnValue(), DebugHudTextCallback.Side.RIGHT);
    }
}
