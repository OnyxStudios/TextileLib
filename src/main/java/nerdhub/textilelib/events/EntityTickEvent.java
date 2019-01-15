package nerdhub.textilelib.events;

import net.minecraft.entity.Entity;

public class EntityTickEvent extends TickEvent {

    private Entity entity;

    public EntityTickEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
