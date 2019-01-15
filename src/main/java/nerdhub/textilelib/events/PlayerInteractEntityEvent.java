package nerdhub.textilelib.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class PlayerInteractEntityEvent extends PlayerInteractEvent {

    private Entity target;

    public PlayerInteractEntityEvent(PlayerEntity player, Hand hand, Entity target) {
        super(player, hand);
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }
}
