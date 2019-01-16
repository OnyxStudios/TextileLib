package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;

import java.util.List;

public class TooltipBuildEvent implements Event {

    private ItemStack stack;
    private List<TextComponent> list;
    private TooltipOptions tooltipOptions;

    public TooltipBuildEvent(ItemStack stack, List<TextComponent> list, TooltipOptions tooltipOptions) {
        this.stack = stack;
        this.list = list;
        this.tooltipOptions = tooltipOptions;
    }

    public ItemStack getStack() {
        return stack;
    }

    public List<TextComponent> getList() {
        return list;
    }

    public TooltipOptions getTooltipOptions() {
        return tooltipOptions;
    }
}
