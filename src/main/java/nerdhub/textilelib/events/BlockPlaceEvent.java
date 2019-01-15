package nerdhub.textilelib.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPlaceEvent extends BlockEvent {

    private Entity placer;
    private BlockState placedOn;

    public BlockPlaceEvent(World world, BlockPos pos, BlockState state, @Nullable Entity placer, BlockState placedOn) {
        super(world, pos, state);
        this.placer = placer;
        this.placedOn = placedOn;
    }

    public Entity getPlacer() {
        return placer;
    }

    public BlockState getPlacedOn() {
        return placedOn;
    }
}
