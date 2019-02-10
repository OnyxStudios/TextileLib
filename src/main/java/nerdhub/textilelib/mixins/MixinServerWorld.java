package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.EntitySpawnedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld extends MixinWorld {

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void spawnEntity(Entity entity_1, CallbackInfoReturnable<Boolean> cir) {
        int int_1 = MathHelper.floor(entity_1.x / 16.0D);
        int int_2 = MathHelper.floor(entity_1.z / 16.0D);
        if(entity_1.teleporting || entity_1 instanceof PlayerEntity || ((World) (Object) this).isChunkLoaded(int_1, int_2)) {
            EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity_1);
            EventRegistry.INSTANCE.fireEvent(entityAddedEvent);
            if(entityAddedEvent.isCanceled()) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
