package nerdhub.textilelib.events;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawnPortalEvent extends BlockEvent {

    public SpawnPortalEvent(World world, BlockPos pos, BlockState state) {
        super(world, pos, state);
    }
}
