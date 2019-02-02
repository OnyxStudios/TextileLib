package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.entity.EntitySpawnedEvent;
import nerdhub.textilelib.events.tick.WorldTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

@Mixin(World.class)
public abstract class MixinWorld {

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

    @Inject(method = "loadEntities", at = @At("HEAD"), cancellable = true)
    protected void loadEntities(Stream<Entity> stream_1, CallbackInfo ci) {
        stream_1.forEach((entity_1) -> {
            EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity_1);
            EventRegistry.INSTANCE.fireEvent(entityAddedEvent);
            if(!entityAddedEvent.isCanceled()) {
                ((World) (Object) this).entities.add(entity_1);
                this.onEntityAdded(entity_1);
            }
        });
        ci.cancel();
    }

    @Shadow
    protected abstract void onEntityAdded(Entity entity_1);

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(BooleanSupplier booleanSupplier_1, CallbackInfo ci) {
        WorldTickEvent worldTickEvent = new WorldTickEvent((World) (Object) this);
        EventRegistry.INSTANCE.fireEvent(worldTickEvent);
    }
}
