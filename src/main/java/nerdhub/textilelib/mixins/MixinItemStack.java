package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.block.BlockPlaceEvent;
import nerdhub.textilelib.events.render.TooltipBuildEvent;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.block.BlockItem;
import net.minecraft.text.TextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if(context.getItemStack().getItem() instanceof BlockItem) {
            World world = context.getWorld();
            BlockPos pos = context.getBlockPos();
            Direction facing = context.getFacing();
            ItemStack stack = context.getItemStack();
            PlayerEntity playerEntity = context.getPlayer();
            BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(world, pos, Block.getBlockFromItem(stack.getItem()).getDefaultState(), playerEntity, world.getBlockState(pos.offset(facing)));
            EventRegistry.INSTANCE.fireEvent(blockPlaceEvent);
            if(blockPlaceEvent.isCanceled()) {
                cir.setReturnValue(ActionResult.PASS);
                cir.cancel();
            }
        }
    }

    @Inject(method = "getTooltipText", at = @At("RETURN"))
    private void getTooltipText(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<TextComponent>> cir) {
        TooltipBuildEvent tooltipBuildEvent = new TooltipBuildEvent((ItemStack) (Object) this, cir.getReturnValue(), context);
        EventRegistry.INSTANCE.fireEvent(tooltipBuildEvent);
    }
}
