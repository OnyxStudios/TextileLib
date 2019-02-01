package nerdhub.textilelib.events.entity.player;

import net.fabricmc.api.*;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Is called on client side only, send a packed with the data to server to handle stuff
 */
@Environment(EnvType.CLIENT)
public class PlayerSwingEvent extends PlayerEvent {

    public PlayerSwingEvent(PlayerEntity player) {
        super(player);
    }
}
