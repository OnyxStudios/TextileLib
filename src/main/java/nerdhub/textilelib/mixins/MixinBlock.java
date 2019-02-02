package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.block.BlockDropsEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Block.class)
public abstract class MixinBlock {

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    private void afterBreak(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, BlockState blockState_1, @Nullable BlockEntity blockEntity_1, ItemStack itemStack_1, CallbackInfo ci) {
        if(!world_1.isClient) {
            BlockDropsEvent blockDropsEvent = new BlockDropsEvent(world_1, blockPos_1, blockState_1, Block.getDroppedStacks(blockState_1, (ServerWorld) world_1, blockPos_1, blockEntity_1), playerEntity_1, itemStack_1);
            EventRegistry.INSTANCE.fireEvent(blockDropsEvent);
            if(blockDropsEvent.isCanceled()) {
                ci.cancel();
            }
        }
    }
}
