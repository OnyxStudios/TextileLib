package nerdhub.textilelib.events.render;

import nerdhub.textilelib.events.Event;
import net.minecraft.client.gui.Gui;

public class GuiDrawEvent implements Event {

    private Gui gui;
    private int mouseX, mouseY;
    private float partialTicks;

    public GuiDrawEvent(Gui gui, int mouseX, int mouseY, float partialTicks) {
        this.gui = gui;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
    }

    public Gui getGui() {
        return gui;
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
