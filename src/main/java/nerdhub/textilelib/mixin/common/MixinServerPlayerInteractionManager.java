package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.entity.player.BreakBlockCallback;
import net.minecraft.client.network.packet.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class MixinServerPlayerInteractionManager {

    @Shadow public ServerWorld world;

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    private void destroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(BreakBlockCallback.EVENT.invoker().onBlockBroken(world, pos, world.getBlockState(pos), player)) {
            // Update client as they will believe they have broken the block.
            if(this.player != null) {
                this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(world, pos));
            }
            cir.setReturnValue(false);
        }
    }
}
