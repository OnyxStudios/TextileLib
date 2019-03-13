package nerdhub.textilelib.event.client.render.hud;

import java.util.List;

import net.minecraft.client.gui.hud.DebugHud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public interface DebugHudTextCallback {

    Event<DebugHudTextCallback> EVENT = EventFactory.createArrayBacked(DebugHudTextCallback.class, listeners -> (List<String> list, Side side) -> {
        for (DebugHudTextCallback callback : listeners) {
            callback.getText(list, side);
        }
    });

    /**
     * Called after the {@link DebugHud} has collected the list of strings to draw, to allow for rendering additional information
     */
    void getText(List<String> list, Side side);

    enum Side {LEFT, RIGHT}
}
