package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.CancelableEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TooltipDrawEvent extends CancelableEvent {

    private ItemStack stack;
    private List<String> tooltipList;
    private int x, y;

    public TooltipDrawEvent(ItemStack stack, List<String> tooltipList, int x, int y) {
        this.stack = stack;
        this.tooltipList = tooltipList;
        this.x = x;
        this.y = y;
    }
}
