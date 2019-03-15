package nerdhub.textilelib.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.*;
import net.minecraft.scoreboard.ScoreboardObjective;

import nerdhub.textilelib.event.client.render.hud.DrawHudCallback;
import nerdhub.textilelib.event.client.render.hud.HudTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void renderScoreboardSidebar(ScoreboardObjective scoreboardObjective_1);

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;draw(F)V"), method = "draw")
    private void drawSpectatorHud(SpectatorHud hud, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudSpectator");
        this.client.getProfiler().push("textilelib:renderHudSpectatorBefore");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.SPECTATOR, hud, deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudSpectatorDraw");
            hud.draw(deltaTime);
            this.client.getProfiler().swap("textilelib:renderHudSpectatorAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.SPECTATOR, hud, deltaTime);
        }
        this.client.getProfiler().pop();
        this.client.getProfiler().pop();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/BossBarHud;draw()V"), method = "draw")
    private void drawBossBarHud(BossBarHud hud, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudBossbarBefore");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.BOSSBAR, hud, deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudBossbarDraw");
            hud.draw();
            this.client.getProfiler().swap("textilelib:renderHudBossbarAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.BOSSBAR, hud, deltaTime);
        }
        this.client.getProfiler().pop();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;draw()V"), method = "draw")
    private void drawDebugHud(DebugHud hud, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudDebug");
        this.client.getProfiler().push("textilelib:renderHudDebugBefore");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.DEBUG, hud, deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudDebugDraw");
            hud.draw();
            this.client.getProfiler().swap("textilelib:renderHudDebugAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.DEBUG, hud, deltaTime);
        }
        this.client.getProfiler().pop();
        this.client.getProfiler().pop();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SubtitlesHud;draw()V"), method = "draw")
    private void drawSubtitlesHud(SubtitlesHud hud, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudSubtitles");
        this.client.getProfiler().push("textilelib:renderHudSubtitlesBefore");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.SUBTITLES, hud, deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudSubtitlesDraw");
            hud.draw();
            this.client.getProfiler().swap("textilelib:renderHudSubtitlesAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.SUBTITLES, hud, deltaTime);
        }
        this.client.getProfiler().pop();
        this.client.getProfiler().pop();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/scoreboard/ScoreboardObjective;)V"), method = "draw")
    private void drawScoreboardHud(InGameHud hud, ScoreboardObjective scoreboardObjective, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudScoreboard");
        this.client.getProfiler().push("textilelib:renderHudScoreboardBefore");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.SCOREBOARD, hud.getScoreboardWidget(), deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudScoreboardDraw");
            this.renderScoreboardSidebar(scoreboardObjective);
            this.client.getProfiler().swap("textilelib:renderHudScoreboardAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.SCOREBOARD, hud.getScoreboardWidget(), deltaTime);
        }
        this.client.getProfiler().pop();
        this.client.getProfiler().pop();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;draw(I)V"), method = "draw")
    private void drawChatHud(ChatHud hud, int int_1, float deltaTime) {
        this.client.getProfiler().push("textilelib:renderHudChat");
        if (DrawHudCallback.EVENT_BEFORE.invoker().drawHud(HudTypes.CHAT, hud, deltaTime)) {
            this.client.getProfiler().swap("textilelib:renderHudChatDraw");
            hud.draw(int_1);
            this.client.getProfiler().swap("textilelib:renderHudChatAfter");
            DrawHudCallback.EVENT_AFTER.invoker().drawHud(HudTypes.CHAT, hud, deltaTime);
        }
        this.client.getProfiler().pop();
    }


    private void drawHearts() {
        // TODO: In order to allow the cancelling the status bars, we would need to overwrite the entire thing
    }

    private void drawArmor() {
        // TODO: In order to allow the cancelling the status bars, we would need to overwrite the entire thing
    }

    private void drawAir() {
        // TODO: In order to allow the cancelling the status bars, we would need to overwrite the entire thing
    }

    private void drawFood() {
        // TODO: In order to allow the cancelling the status bars, we would need to overwrite the entire thing
    }
}
