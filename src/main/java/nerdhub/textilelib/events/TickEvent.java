package nerdhub.textilelib.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public abstract class TickEvent implements Event {

    public TickEvent() {
    }

    public static class ServerTickEvent extends TickEvent {

        private MinecraftServer server;

        public ServerTickEvent(MinecraftServer server) {
            this.server = server;
        }

        public MinecraftServer getServer() {
            return server;
        }
    }

    public static class ClientTickEvent extends TickEvent {

        public ClientTickEvent() {
        }
    }

    public static class EntityTickEvent extends TickEvent {

        private Entity entity;

        public EntityTickEvent(Entity entity) {
            this.entity = entity;
        }

        public Entity getEntity() {
            return entity;
        }
    }

    public static class PlayerTickEvent extends TickEvent {

        private PlayerEntity player;

        public PlayerTickEvent(PlayerEntity player) {
            this.player = player;
        }

        public PlayerEntity getPlayer() {
            return player;
        }
    }

    public static class WorldTickEvent extends TickEvent {

        private World world;

        public WorldTickEvent(World world) {
            this.world = world;
        }

        public World getWorld() {
            return world;
        }
    }
}
