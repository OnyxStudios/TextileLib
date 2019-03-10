package nerdhub.textilelib.event.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public interface PlayerDeathCallback {

    Event<PlayerDeathCallback> EVENT = EventFactory.createArrayBacked(PlayerDeathCallback.class, listeners -> (world, player, damageSource) -> {
        for (PlayerDeathCallback callback : listeners) {
            if(!callback.onPlayerDeath(world, player, damageSource)) {
                return false;
            }
        }

        return true;
    });

    /**
     * Fired when a player dies
     * @param world - ServerWorld parameter
     * @param player - The player who died
     * @param damageSource - The damagesource
     * @return {@code false} to cancel the event
     */
    boolean onPlayerDeath(ServerWorld world, ServerPlayerEntity player, DamageSource damageSource);
}
