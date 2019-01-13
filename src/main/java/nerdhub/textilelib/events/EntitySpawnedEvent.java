package nerdhub.textilelib.events;

import net.minecraft.entity.Entity;

public class EntitySpawnedEvent extends CancelableEvent {

    private Entity entity;

    public EntitySpawnedEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
