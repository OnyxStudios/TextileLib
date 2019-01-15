package nerdhub.textilelib;

import com.mojang.blaze3d.platform.GlStateManager;
import nerdhub.textilelib.eventhandlers.EventSubscriber;
import nerdhub.textilelib.events.render.RenderWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.FluidRayTraceMode;

public class RenderEventHandler {

    @EventSubscriber
    public static void renderWorldLast(RenderWorldEvent event) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        HitResult result = rayTrace(player, 15, event.getPartialTicks(), FluidRayTraceMode.SOURCE_ONLY);
        player.world.addImportantParticle(ParticleTypes.CRIT, result.pos.x, result.pos.y + 0.001D, result.pos.z, 0.0D, 0.0D, 0.0D);
        renderPlayer(player, MinecraftClient.getInstance().world, result.pos.x, result.pos.y, result.pos.z, event.getPartialTicks());
    }

    public static void renderPlayer(ClientPlayerEntity player, ClientWorld world, double x, double y, double z, float partialTicks) {
        GlStateManager.pushMatrix();
        {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderManager();
            Entity cameraEntity = MinecraftClient.getInstance().getCameraEntity();
            double dX = 0.0D;
            double dY = 0.0D;
            double dZ = 0.0D;
            if(cameraEntity != null) {
                dX = MathHelper.lerp(partialTicks, cameraEntity.prevRenderX, cameraEntity.x);
                dY = MathHelper.lerp(partialTicks, cameraEntity.prevRenderY, cameraEntity.y);
                dZ = MathHelper.lerp(partialTicks, cameraEntity.prevRenderZ, cameraEntity.z);
            }
            dispatcher.render(player, x - dX, y - dY, z -dZ, 0, 1.0F, false); //boolean: renderHitbox, flaot: partialTicks
        }
        GlStateManager.popMatrix();
    }

    public static HitResult rayTrace(Entity entity, double range, float partialTicks, FluidRayTraceMode fluidMode) {
        Vec3d startPoint = entity.getCameraPosVec(partialTicks);
        Vec3d lookVec = entity.getRotationVec(partialTicks);
        Vec3d endPoint = startPoint.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);

        HitResult ret = entity.world.rayTrace(startPoint, endPoint, fluidMode, false, true);
        return ret != null ? ret : new HitResult(HitResult.Type.NONE, endPoint, null, new BlockPos(endPoint)); //TODO check those boolean params
    }
}
