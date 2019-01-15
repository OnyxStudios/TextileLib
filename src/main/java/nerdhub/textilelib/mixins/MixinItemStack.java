package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.block.BlockPlaceEvent;
import nerdhub.textilelib.events.render.TooltipBuildEvent;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipOptions;
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
public class MixinItemStack {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext itemUsageContext_1, CallbackInfoReturnable cir) {
        if (itemUsageContext_1.getItemStack().getItem() instanceof BlockItem) {
            World world = itemUsageContext_1.getWorld();
            BlockPos pos = itemUsageContext_1.getPos();
            Direction facing = itemUsageContext_1.getFacing();
            ItemStack stack = itemUsageContext_1.getItemStack();
            PlayerEntity playerEntity = itemUsageContext_1.getPlayer();

            BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(world, pos, Block.getBlockFromItem(stack.getItem()).getDefaultState(), playerEntity, world.getBlockState(pos.offset(facing)));
            EventRegistry.INSTANCE.fireEvent(blockPlaceEvent);

            if (blockPlaceEvent.isCanceled()) {
                cir.setReturnValue(ActionResult.PASS);
                cir.cancel();
            }
        }
    }

    @Inject(method = "getTooltipText", at = @At("RETURN"))
    public void getTooltipText(@Nullable PlayerEntity playerEntity_1, TooltipOptions tooltipOptions_1, CallbackInfoReturnable cir) {
        TooltipBuildEvent tooltipBuildEvent = new TooltipBuildEvent((ItemStack) (Object) this, (List<TextComponent>) cir.getReturnValue());
        EventRegistry.INSTANCE.fireEvent(tooltipBuildEvent);
    }
}
