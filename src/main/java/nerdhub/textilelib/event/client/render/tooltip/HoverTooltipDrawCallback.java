package nerdhub.textilelib.event.client.render.tooltip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface HoverTooltipDrawCallback {

    Event<HoverTooltipDrawCallback> EVENT = EventFactory.createArrayBacked(HoverTooltipDrawCallback.class, listeners -> (stack, tooltipList, x, y) -> {
        for (HoverTooltipDrawCallback callback : listeners) {
            if(!callback.drawTooltip(stack, tooltipList, x, y)) {
                return false;
            }
        }

        return true;
    });

    boolean drawTooltip(ItemStack stack, List<String> tooltipList, int x, int y);
}
