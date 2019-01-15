package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;

import java.util.List;

public class TooltipBuildEvent implements Event {

    private ItemStack stack;
    private List<TextComponent> list;

    public TooltipBuildEvent(ItemStack stack, List<TextComponent> list) {
        this.stack = stack;
        this.list = list;
    }

    public ItemStack getStack() {
        return stack;
    }

    public List<TextComponent> getList() {
        return list;
    }
}
