package nerdhub.textilelib.mixins;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import nerdhub.textilelib.events.EntitySpawnedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Stream;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld extends MixinWorld {

    @Inject(method = "loadEntities", at = @At("HEAD"), cancellable = true)
    public void loadEntities(Stream<Entity> stream_1, CallbackInfo ci) {
        stream_1.forEach((entity_1) -> {
            if (this.method_14175(entity_1)) {
                EntitySpawnedEvent entityAddedEvent = new EntitySpawnedEvent(entity_1);
                EventRegistry.INSTANCE.fireEvent(entityAddedEvent);
                if(!entityAddedEvent.isCanceled()) {
                    this.entities.add(entity_1);
                    this.onEntityAdded(entity_1);
                }
            }
        });

        ci.cancel();
    }

    //TODO map to canLoadEntity
    @Shadow
    abstract boolean method_14175(Entity entity_1);

    @Shadow
    abstract void onEntityAdded(Entity entity_1);
}
