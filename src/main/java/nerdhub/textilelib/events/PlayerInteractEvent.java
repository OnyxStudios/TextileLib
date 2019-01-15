package nerdhub.textilelib.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class PlayerInteractEvent extends PlayerEvent {

    private Hand hand;

    public PlayerInteractEvent(PlayerEntity player, Hand hand) {
        super(player);
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}
