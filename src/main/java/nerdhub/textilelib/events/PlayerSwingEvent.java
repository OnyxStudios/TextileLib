package nerdhub.textilelib.events;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Is called on client side only, send a packed with the data to server to handle stuff
 */
public class PlayerSwingEvent extends PlayerEvent {

    public PlayerSwingEvent(PlayerEntity player) {
        super(player);
    }
}
