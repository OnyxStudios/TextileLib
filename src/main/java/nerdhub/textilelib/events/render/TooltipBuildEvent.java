package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TooltipBuildEvent implements Event {

    private ItemStack stack;
    private List<TextComponent> list;
    private TooltipContext tooltipOptions;

    public TooltipBuildEvent(ItemStack stack, List<TextComponent> list, TooltipContext tooltipOptions) {
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

    public TooltipContext getTooltipOptions() {
        return tooltipOptions;
    }
}
