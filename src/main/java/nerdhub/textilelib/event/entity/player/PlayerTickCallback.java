package nerdhub.textilelib.event.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerTickCallback {

    Event<PlayerTickCallback> EVENT = EventFactory.createArrayBacked(PlayerTickCallback.class, listeners -> player -> {
        for(PlayerTickCallback callback : listeners) {
            callback.tick(player);
        }
    });

    /**
     * fired before player update logic runs
     */
    void tick(PlayerEntity player);
}
