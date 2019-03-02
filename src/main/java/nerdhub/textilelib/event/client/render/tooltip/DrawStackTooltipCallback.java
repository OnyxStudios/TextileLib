package nerdhub.textilelib.event.client.render.tooltip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface DrawStackTooltipCallback {

    Event<DrawStackTooltipCallback> EVENT = EventFactory.createArrayBacked(DrawStackTooltipCallback.class, listeners -> (stack, tooltipList, mouseX, mouseY) -> {
        for (DrawStackTooltipCallback callback : listeners) {
            if(!callback.drawTooltip(stack, tooltipList, mouseX, mouseY)) {
                return false;
            }
        }

        return true;
    });

    /**
     * Called right before a tooltip is about to be drawn
     *
     * @return - {@code false} to cancel the event
     */
    boolean drawTooltip(ItemStack stack, List<String> tooltipList, int mouseX, int mouseY);
}
