package nerdhub.textilelib.event.client.player;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public interface PlayerSwingCallback {

    Event<PlayerSwingCallback> EVENT = EventFactory.createArrayBacked(PlayerSwingCallback.class, listeners -> player -> {
        for(PlayerSwingCallback callback : listeners) {
            if(callback.onPlayerSwing(player)) {
                return true;
            }
        }
        return false;
    });

    /**
     * fired when the player is about to swing his hand, before any other interaction is handled
     *
     * @return {@code true} to cancel the swing
     */
    boolean onPlayerSwing(PlayerEntity player);
}
