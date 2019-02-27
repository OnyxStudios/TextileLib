package nerdhub.textilelib.event.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.Screen;

@Environment(EnvType.CLIENT)
public interface DrawScreenCallback {

    Event<DrawScreenCallback> EVENT = EventFactory.createArrayBacked(DrawScreenCallback.class, listeners -> (screen, mouseX, mouseY, deltaTime) -> {
        for(DrawScreenCallback callback : listeners) {
            callback.drawScreen(screen, mouseX, mouseY, deltaTime);
        }
    });

    void drawScreen(Screen screen, int mouseX, int mouseY, float deltaTime);
}
