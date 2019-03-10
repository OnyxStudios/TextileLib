package nerdhub.textilelib.event.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public interface PlayerJoinCallback {

    Event<PlayerJoinCallback> EVENT = EventFactory.createArrayBacked(PlayerJoinCallback.class, listeners -> (world, player) -> {
        for (PlayerJoinCallback callback : listeners) {
            callback.onPlayerJoin(world, player);
        }
    });

    /**
     * Fired when a player joins a world
     */
    void onPlayerJoin(ServerWorld world, ServerPlayerEntity player);
}
