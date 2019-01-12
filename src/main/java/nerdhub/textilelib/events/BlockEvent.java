package nerdhub.textilelib.events;

import nerdhub.textilelib.eventhandlers.CancelableEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockEvent extends CancelableEvent {

    private World world;
    private BlockPos pos;
    private BlockState state;

    public BlockEvent(World world, BlockPos pos, BlockState state) {
        this.world = world;
        this.pos = pos;
        this.state = state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public static class BlockBreakEvent extends BlockEvent {

        private PlayerEntity player;

        public BlockBreakEvent(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            super(world, pos, state);
            this.player = player;
        }

        public PlayerEntity getPlayer() {
            return player;
        }
    }

    public static class BlockDropsEvent extends BlockEvent {

        private List<ItemStack> drops;
        private PlayerEntity blockHarvester;
        private ItemStack tool;

        public BlockDropsEvent(World world, BlockPos pos, BlockState state, List<ItemStack> drops, @Nullable PlayerEntity blockHarvester, ItemStack tool) {
            super(world, pos, state);
            this.drops = drops;
            this.blockHarvester = blockHarvester;
            this.tool = tool;
        }

        public List<ItemStack> getDrops() {
            return drops;
        }

        public PlayerEntity getPlayer() {
            return blockHarvester;
        }

        public ItemStack getTool() {
            return tool;
        }
    }

    public static class BlockPlaceEvent extends BlockEvent {

        private PlayerEntity placer;
        private BlockState placedOn;

        public BlockPlaceEvent(World world, BlockPos pos, BlockState state, PlayerEntity placer, BlockState placedOn) {
            super(world, pos, state);
            this.placer = placer;
            this.placedOn = placedOn;
        }

        public PlayerEntity getPlacer() {
            return placer;
        }

        public BlockState getPlacedOn() {
            return placedOn;
        }
    }
}
