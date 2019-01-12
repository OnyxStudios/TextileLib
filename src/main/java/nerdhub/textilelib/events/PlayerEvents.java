package nerdhub.textilelib.events;

import nerdhub.textilelib.eventhandlers.CancelableEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PlayerEvents extends CancelableEvent {

    private PlayerEntity player;
    private Hand hand;
    private BlockPos pos;
    private Direction direction;

    public PlayerEvents(PlayerEntity player, Hand hand) {
        this.player = player;
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public Hand getHand() {
        return hand;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Direction getDirection() {
        return direction;
    }

    public static class PlayerInteractEvent extends PlayerEvents {

        private Entity target;

        public PlayerInteractEvent(PlayerEntity player, Hand hand, Entity target) {
            super(player, hand);
            this.target = target;
        }

        public Entity getTarget() {
            return target;
        }
    }

    /**
     * Is called on client side only, send a packed with the data to server to handle stuff
     */
    public static class PlayerSwingEvent extends PlayerEvents {

        public PlayerSwingEvent(PlayerEntity player) {
            super(player, Hand.MAIN);
        }
    }
}
