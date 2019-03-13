package nerdhub.textilelib.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.*;

import nerdhub.textilelib.event.client.render.hud.DrawHudCallback;
import nerdhub.textilelib.event.client.render.hud.VanillaHudTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    @Final
    private DebugHud debugHud;

    @Shadow
    @Final
    private BossBarHud bossBarHud;

    @Shadow
    @Final
    private SpectatorHud spectatorHud;

    @Shadow
    @Final
    private SubtitlesHud subtitlesHud;

    @Shadow
    @Final
    private ScoreboardHud scoreboardHud;

    @Shadow
    @Final
    private ChatHud chatHud;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;draw(F)V", shift = At.Shift.AFTER), method = "draw")
    private void drawSpectatorHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_spectator");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.SPECTATOR, this.spectatorHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/BossBarHud;draw()V", shift = At.Shift.AFTER), method = "draw")
    private void drawBossBarHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_bossbar");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.BOSSBAR, this.bossBarHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;draw()V", shift = At.Shift.AFTER), method = "draw")
    private void drawDebugHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_debug");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.DEBUG, this.debugHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SubtitlesHud;draw()V", shift = At.Shift.AFTER), method = "draw")
    private void drawSubtitlesHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_subtitles");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.SUBTITLES, this.subtitlesHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER), method = "draw")
    private void drawScoreboardHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_scoreboard");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.SCOREBOARD, this.scoreboardHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;draw(I)V", shift = At.Shift.AFTER), method = "draw")
    private void drawChatHud(float deltaTime, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_hud_chat");
        DrawHudCallback.EVENT.invoker().drawHud(VanillaHudTypes.CHAT, this.chatHud, this.getMouseX(), this.getMouseY(), deltaTime);
    }

    private int getMouseX() {
        return (int) (this.client.mouse.getX() * (double) this.client.window.getScaledWidth() / (double) this.client.window.getWidth());
    }

    private int getMouseY() {
        return (int) (this.client.mouse.getY() * (double) this.client.window.getScaledHeight() / (double) this.client.window.getHeight());
    }
}
