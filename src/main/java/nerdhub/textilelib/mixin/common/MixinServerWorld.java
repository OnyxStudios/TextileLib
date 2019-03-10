package nerdhub.textilelib.mixin.common;

import nerdhub.textilelib.event.entity.EntitySpawnCallback;
import nerdhub.textilelib.event.entity.player.PlayerJoinCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld {

    @Inject(method = "method_18771", at = @At("RETURN"))
    private void method_18771(ServerPlayerEntity serverPlayerEntity, CallbackInfo ci) {
        PlayerJoinCallback.EVENT.invoker().onPlayerJoin(serverPlayerEntity.getServerWorld(), serverPlayerEntity);
    }

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void spawnEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        int chunkX = MathHelper.floor(entity.x / 16.0D);
        int chunkZ = MathHelper.floor(entity.z / 16.0D);
        if(entity.teleporting || entity instanceof PlayerEntity || ((World) (Object) this).isChunkLoaded(chunkX, chunkZ)) {
            if(EntitySpawnCallback.EVENT.invoker().spawnEntity(entity)) {
                cir.setReturnValue(false);
            }
        }
    }
}
