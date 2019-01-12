package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.EntitySpawnedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Stream;

@Mixin(World.class)
public abstract class MixinWorld {

    @Shadow
    @Final
    public List<Entity> entities;

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    public void spawnEntity(Entity entity_1, CallbackInfoReturnable cir) {
        EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity_1.world, entity_1);
        boolean flag = entity_1.field_5983;
        int int_1 = MathHelper.floor(entity_1.x / 16.0D);
        int int_2 = MathHelper.floor(entity_1.z / 16.0D);
        if (entity_1 instanceof PlayerEntity) {
            flag = true;
        }

        if (flag && ((World) (Object) this).isChunkLoaded(int_1, int_2)) {
            EventRegistry.fireEvent(entityAddedEvent);
            if (entityAddedEvent.isCanceled()) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    @Overwrite
    public void loadEntities(Stream<Entity> stream_1) {
        stream_1.forEach((entity_1) -> {
            EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity_1.world, entity_1);
            EventRegistry.fireEvent(entityAddedEvent);
            if (!entityAddedEvent.isCanceled()) {
                this.entities.add(entity_1);
                this.onEntityAdded(entity_1);
            }
        });
    }

    @Shadow
    abstract void onEntityAdded(Entity entity_1);
}
