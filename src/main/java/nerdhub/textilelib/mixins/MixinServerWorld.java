package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.EntitySpawnedEvent;
import nerdhub.textilelib.events.tick.WorldTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld {

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void spawnEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        int chunkX = MathHelper.floor(entity.x / 16.0D);
        int chunkZ = MathHelper.floor(entity.z / 16.0D);
        if(entity.teleporting || entity instanceof PlayerEntity || ((World) (Object) this).isChunkLoaded(chunkX, chunkZ)) {
            EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity);
            EventRegistry.INSTANCE.fireEvent(entityAddedEvent);
            if(entityAddedEvent.isCanceled()) {
                cir.setReturnValue(false);
            }
        }
    }

    //was previously World#tick()
    @Inject(method = "method_18765", at = @At("HEAD"))
    private void tick(BooleanSupplier booleanSupplier_1, CallbackInfo ci) {
        WorldTickEvent worldTickEvent = new WorldTickEvent((World) (Object) this);
        EventRegistry.INSTANCE.fireEvent(worldTickEvent);
    }
}
