package nerdhub.textilelib.events;

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
