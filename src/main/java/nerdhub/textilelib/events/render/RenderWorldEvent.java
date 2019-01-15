package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.fabricmc.api.*;
import net.minecraft.client.render.WorldRenderer;

@Environment(EnvType.CLIENT)
public class RenderWorldEvent implements Event {

    private WorldRenderer worldRenderer;
    private float partialTicks;

    public RenderWorldEvent(WorldRenderer worldRenderer, float partialTicks) {
        this.worldRenderer = worldRenderer;
        this.partialTicks = partialTicks;
    }

    public WorldRenderer getWorldRenderer() {
        return worldRenderer;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
