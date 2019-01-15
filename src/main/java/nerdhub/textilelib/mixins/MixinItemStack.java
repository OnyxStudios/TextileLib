package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.block.BlockPlaceEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void useOnBlock(ItemUsageContext itemUsageContext_1, CallbackInfoReturnable<ActionResult> cir) {
        if(itemUsageContext_1.getItemStack().getItem() instanceof BlockItem) {
            World world = itemUsageContext_1.getWorld();
            BlockPos pos = itemUsageContext_1.getPos();
            Direction facing = itemUsageContext_1.getFacing();
            ItemStack stack = itemUsageContext_1.getItemStack();
            PlayerEntity playerEntity = itemUsageContext_1.getPlayer();
            BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(world, pos, Block.getBlockFromItem(stack.getItem()).getDefaultState(), playerEntity, world.getBlockState(pos.offset(facing)));
            EventRegistry.INSTANCE.fireEvent(blockPlaceEvent);
            if(blockPlaceEvent.isCanceled()) {
                cir.setReturnValue(ActionResult.PASS);
                cir.cancel();
            }
        }
    }
}
