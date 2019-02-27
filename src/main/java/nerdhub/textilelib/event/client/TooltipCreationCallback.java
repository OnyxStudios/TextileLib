package nerdhub.textilelib.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;

import java.util.List;


@Environment(EnvType.CLIENT)
public interface TooltipCreationCallback {

    Event<TooltipCreationCallback> EVENT = EventFactory.createArrayBacked(TooltipCreationCallback.class, listeners -> (stack, context, tooltip) -> {
        for(TooltipCreationCallback callback : listeners) {
            callback.buildTooltip(stack, context, tooltip);
        }
    });

    void buildTooltip(ItemStack stack, TooltipContext context, List<TextComponent> tooltip);
}
