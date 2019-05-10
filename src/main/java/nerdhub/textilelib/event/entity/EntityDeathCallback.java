package nerdhub.textilelib.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;

public interface EntityDeathCallback {

    Event<EntityDeathCallback> EVENT = EventFactory.createArrayBacked(EntityDeathCallback.class, listeners -> (world, entity, damageSource) -> {
        for (EntityDeathCallback callback : listeners) {
            if(!callback.onEntityDeath(world, entity, damageSource)) {
                return false;
            }
        }

        return true;
    });

    /**
     * Fired when an entity dies
     * @param world - The world
     * @param entity - The entity who died
     * @param damageSource - The source of damage
     * @return {@code false} to cancel the event
     */
    boolean onEntityDeath(World world, LivingEntity entity, DamageSource damageSource);
}
