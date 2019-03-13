package nerdhub.textilelib.event.client.render.hud;

import net.minecraft.client.gui.DrawableHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public interface DrawHudCallback {

    Event<DrawHudCallback> EVENT = EventFactory.createArrayBacked(DrawHudCallback.class, listeners -> (type, hud, mouseX, mouseY, deltaTime) -> {
        for (DrawHudCallback callback : listeners) {
            callback.drawHud(type, hud, mouseX, mouseY, deltaTime);
        }
    });

    /**
     * Called after the {@link DrawableHelper} is drawn, to allow for rendering additional elements
     */
    void drawHud(IHudType type, DrawableHelper hud, int mouseX, int mouseY, float deltaTime);
}
