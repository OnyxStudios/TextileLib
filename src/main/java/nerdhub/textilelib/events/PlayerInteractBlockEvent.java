package nerdhub.textilelib.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class PlayerInteractBlockEvent extends PlayerInteractEvent {

    private BlockState state;
    private BlockPos pos;

    public PlayerInteractBlockEvent(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
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
