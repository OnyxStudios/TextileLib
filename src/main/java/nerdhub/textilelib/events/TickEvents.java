package nerdhub.textilelib.events;

import nerdhub.textilelib.eventhandlers.Event;

public class TickEvents implements Event {

    public TickEvents() {
    }

    public static class ServerTickEvent extends TickEvents {

        public ServerTickEvent() {
        }
    }

    public static class ClientTickEvent extends TickEvents {

        public ClientTickEvent() {
        }
    }

    public static class EntityTickEvent extends TickEvents {

        public EntityTickEvent() {
        }
    }
}
