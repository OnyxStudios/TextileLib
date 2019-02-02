package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Screen;

@Environment(EnvType.CLIENT)
public class ScreenDrawEvent implements Event {

    private Screen screen;
    private int mouseX, mouseY;
    private float partialTicks;

    public ScreenDrawEvent(Screen screen, int mouseX, int mouseY, float partialTicks) {
        this.screen = screen;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
