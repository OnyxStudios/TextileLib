package nerdhub.textilelib.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;

public interface EntityTickCallback {

    Event<EntityTickCallback> EVENT = EventFactory.createArrayBacked(EntityTickCallback.class, listeners -> entity -> {
        for(EntityTickCallback callback : listeners) {
            callback.tick(entity);
        }
    });

    void tick(Entity entity);
}
