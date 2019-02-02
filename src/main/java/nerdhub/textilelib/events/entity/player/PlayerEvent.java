package nerdhub.textilelib.events.entity.player;

import nerdhub.textilelib.events.CancelableEvent;
import net.minecraft.entity.player.PlayerEntity;

public abstract class PlayerEvent extends CancelableEvent {

    private PlayerEntity player;

    public PlayerEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }
}
