package nerdhub.textilelib.event.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.WorldRenderer;

@Environment(EnvType.CLIENT)
public interface RenderWorldCallback {

    Event<RenderWorldCallback> EVENT = EventFactory.createArrayBacked(RenderWorldCallback.class, listeners -> (worldRenderer, deltaTime) -> {
        for(RenderWorldCallback callback : listeners) {
            callback.render(worldRenderer, deltaTime);
        }
    });

    /**
     * fired as a psot render hook after after the world is rendered, but before any GUI is.
     */
    void render(WorldRenderer worldRenderer, float deltaTime);
}
