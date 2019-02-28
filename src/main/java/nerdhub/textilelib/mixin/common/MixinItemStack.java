package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.client.TooltipCreationCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "getTooltipText", at = @At("RETURN"))
    private void getTooltipText(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<TextComponent>> cir) {
        TooltipCreationCallback.EVENT.invoker().buildTooltip((ItemStack) (Object) this, context, cir.getReturnValue());
    }
}
