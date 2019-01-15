package nerdhub.textilelib.events;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerTickEvent extends TickEvent {

    private PlayerEntity player;

    public PlayerTickEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }
}
