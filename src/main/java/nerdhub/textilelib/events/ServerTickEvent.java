package nerdhub.textilelib.events;

import net.minecraft.server.MinecraftServer;

public class ServerTickEvent extends TickEvent {

    private MinecraftServer server;

    public ServerTickEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer getServer() {
        return server;
    }
}
