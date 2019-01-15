package nerdhub.textilelib.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBreakEvent extends BlockEvent {

    private Entity entity;

    public BlockBreakEvent(World world, BlockPos pos, BlockState state, @Nullable Entity entity) {
        super(world, pos, state);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
