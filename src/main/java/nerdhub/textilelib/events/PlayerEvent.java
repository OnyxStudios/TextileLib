package nerdhub.textilelib.events;

import nerdhub.textilelib.eventhandlers.CancelableEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public abstract class PlayerEvent extends CancelableEvent {

    private PlayerEntity player;

    public PlayerEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public static class InteractEvent extends PlayerEvent {

        private Hand hand;

        public InteractEvent(PlayerEntity player, Hand hand) {
            super(player);
            this.hand = hand;
        }

        public Hand getHand() {
            return hand;
        }
    }

    public static class InteractEntityEvent extends InteractEvent {

        private Entity target;

        public InteractEntityEvent(PlayerEntity player, Hand hand, Entity target) {
            super(player, hand);
            this.target = target;
        }

        public Entity getTarget() {
            return target;
        }
    }

    public static class InteractBlockEvent extends InteractEvent {

        private BlockState state;
        private BlockPos pos;

        public InteractBlockEvent(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
            super(player, hand);
            this.state = state;
            this.pos = pos;
        }

        public BlockState getState() {
            return state;
        }

        public BlockPos getPos() {
            return pos;
        }
    }

    /**
     * Is called on client side only, send a packed with the data to server to handle stuff
     */
    public static class PlayerSwingEvent extends PlayerEvent {

        public PlayerSwingEvent(PlayerEntity player) {
            super(player);
        }
    }
}
