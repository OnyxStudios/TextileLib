package nerdhub.textilelib;

import nerdhub.textilelib.eventhandlers.EventRegistry;
import net.fabricmc.api.ModInitializer;

public class TextileLib implements ModInitializer {

    @Override
    public void onInitialize() {
        EventRegistry.INSTANCE.registerEventHandler(new RenderEventHandler());
    }
}
