package nerdhub.textilelib.event.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

//TODO fire for end portals, too (maybe add some portal type enum?)
public interface PortalCreationCallback {

    Event<PortalCreationCallback> EVENT = EventFactory.createArrayBacked(PortalCreationCallback.class, listeners -> (world, pos, state) -> {
        for(PortalCreationCallback callback : listeners) {
            if(callback.tryCreatePortal(world, pos, state)) {
                return true;
            }
        }
        return false;
    });

    /**
     * fired when a portal is about to be created
     *
     * @return {@code true} to cancel creation of the portal
     */
    boolean tryCreatePortal(IWorld world, BlockPos pos, BlockState state);
}
