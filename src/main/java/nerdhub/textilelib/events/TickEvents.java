package nerdhub.textilelib.events;

import nerdhub.textilelib.eventhandlers.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class TickEvents implements Event {

    public TickEvents() {
    }

    public static class ServerTickEvent extends TickEvents {

        private MinecraftServer server;

        public ServerTickEvent(MinecraftServer server) {
            this.server = server;
        }

        public MinecraftServer getServer() {
            return server;
        }
    }

    public static class ClientTickEvent extends TickEvents {

        public ClientTickEvent() {
        }
    }

    public static class EntityTickEvent extends TickEvents {

        private Entity entity;

        public EntityTickEvent(Entity entity) {
            this.entity = entity;
        }

        public Entity getEntity() {
            return entity;
        }
    }

    public static class PlayerTickEvent extends TickEvents {

        private PlayerEntity player;

        public PlayerTickEvent(PlayerEntity player) {
            this.player = player;
        }

        public PlayerEntity getPlayer() {
            return player;
        }
    }
}
