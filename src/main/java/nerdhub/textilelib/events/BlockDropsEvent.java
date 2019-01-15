package nerdhub.textilelib.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockDropsEvent extends BlockEvent {

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
