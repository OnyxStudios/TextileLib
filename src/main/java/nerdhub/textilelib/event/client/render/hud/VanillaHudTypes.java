package nerdhub.textilelib.event.client.render.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public enum VanillaHudTypes implements IHudType {
    INGAME,
    SPECTATOR,
    DEBUG,
    BOSSBAR,
    CHAT,
    SCOREBOARD,
    SUBTITLES
}
