package nerdhub.textilelib.event.client.render.tooltip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.List;


@Environment(EnvType.CLIENT)
public interface TooltipCreationCallback {

    Event<TooltipCreationCallback> EVENT = EventFactory.createArrayBacked(TooltipCreationCallback.class, listeners -> (stack, context, tooltip) -> {
        for(TooltipCreationCallback callback : listeners) {
            callback.buildTooltip(stack, context, tooltip);
        }
    });

    /**
     * fired to build a stack's tooltip, before it is rendered
     *
     * @param tooltip the list that contains the tooltip
     */
    void buildTooltip(ItemStack stack, TooltipContext context, List<Component> tooltip);
}
