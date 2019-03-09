package nerdhub.textilelib.event.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PlayerJoinCallback {

    Event<PlayerJoinCallback> EVENT = EventFactory.createArrayBacked(PlayerJoinCallback.class, listeners -> (world, player, pos) -> {
        for (PlayerJoinCallback callback : listeners) {
            callback.onPlayerJoin(world, player, pos);
        }
    });

    /**
     * Fired when a player joins a world
     */
    void onPlayerJoin(World world, PlayerEntity player, BlockPos pos);
}
