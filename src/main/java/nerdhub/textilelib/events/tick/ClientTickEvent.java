package nerdhub.textilelib.events.tick;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientTickEvent extends TickEvent {

    public ClientTickEvent() {
    }
}
