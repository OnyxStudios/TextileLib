package nerdhub.textilelib.event.client.render.hud;

import net.minecraft.client.gui.DrawableHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public interface DrawHudCallback {

    interface Pre extends DrawHudCallback {

        Event<Pre> EVENT = EventFactory.createArrayBacked(Pre.class, listeners -> (type, hud, deltaTime) -> {
            for (Pre callback : listeners) {
                if (!callback.drawHud(type, hud, deltaTime)) {
                    return false;
                }
            }
            return true;
        });

        /**
         * Called before the {@link DrawableHelper} is drawn, to allow for rendering additional elements
         *
         * @return True to continue drawing, False to finish/cancel drawing
         */
        boolean drawHud(IHudType type, DrawableHelper hud, float deltaTime);
    }

    interface Post extends DrawHudCallback {

        Event<Post> EVENT = EventFactory.createArrayBacked(Post.class, listeners -> (type, hud, deltaTime) -> {
            for (Post callback : listeners) {
                callback.drawHud(type, hud, deltaTime);
            }
        });

        /**
         * Called after the {@link DrawableHelper} is drawn, to allow for rendering additional elements
         */
        void drawHud(IHudType type, DrawableHelper hud, float deltaTime);
    }
}
