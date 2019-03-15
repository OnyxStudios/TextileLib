package nerdhub.textilelib.event.client.render.hud;

import net.minecraft.client.gui.DrawableHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public interface DrawHudCallback {

    Event<DrawHudCallback> EVENT_BEFORE = EventFactory.createArrayBacked(DrawHudCallback.class, listeners -> (type, hud, deltaTime) -> {
        for (DrawHudCallback callback : listeners) {
            if (!callback.drawHud(type, hud, deltaTime)) {
                return false;
            }
        }
        return true;
    });

    Event<DrawHudCallback> EVENT_AFTER = EventFactory.createArrayBacked(DrawHudCallback.class, listeners -> (type, hud, deltaTime) -> {
        for (DrawHudCallback callback : listeners) {
            if (!callback.drawHud(type, hud, deltaTime)) {
                return false;
            }
        }
        return true;
    });

    /**
     * Called before and after the {@link DrawableHelper} is drawn, to allow for rendering additional elements
     *
     * @return True to continue drawing, False to finish/cancel drawing
     */
    boolean drawHud(IHudType type, DrawableHelper hud, float deltaTime);
}
