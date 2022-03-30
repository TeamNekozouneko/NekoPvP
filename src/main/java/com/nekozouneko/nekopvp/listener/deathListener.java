package com.nekozouneko.nekopvp.listener;

import com.nekozouneko.nekopvp.NekoPvP;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;

public class deathListener implements Listener {

    private NekoPvP plugin;
    public deathListener() {
        plugin = NekoPvP.getInstance();
    }

    @EventHandler
    public void onKilled(PlayerDeathEvent e) {
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        if (this.plugin.getConfig().getBoolean("onDied.enabled")) {
            if (score.getTeam(this.plugin.getConfig().getString("team_one.id")) != null) {
                if (score.getTeam(this.plugin.getConfig().getString("team_one.id"))
                        .getEntries().contains(e.getEntity().getName()) &&
                        !this.plugin.getConfig().getBoolean("team_one.onDiedTrigger")) {
                    return;
                }
            }
            if (score.getTeam(this.plugin.getConfig().getString("team_two.id")) != null) {
                if (score.getTeam(this.plugin.getConfig().getString("team_two.id"))
                        .getEntries().contains(e.getEntity().getName()) &&
                        !this.plugin.getConfig().getBoolean("team_two.onDiedTrigger")) {
                    return;
                }
            }
            switch (this.plugin.getConfig().getString("onDied.triggered")) {
                case "GAMEMODE" -> {
                    switch (this.plugin.getConfig().getString("onDied.value")) {
                        case "ADVENTURE" -> e.getEntity().setGameMode(GameMode.ADVENTURE);
                        case "CREATIVE" -> e.getEntity().setGameMode(GameMode.CREATIVE);
                        case "SPECTATOR" -> e.getEntity().setGameMode(GameMode.SPECTATOR);
                        case "SURVIVAL" -> e.getEntity().setGameMode(GameMode.SURVIVAL);
                        default -> e.getEntity().sendMessage("ゲームモードの変更に失敗しました。config.ymlを確認してください。");
                    }
                }
                case "COMMAND" -> {
                    try {
                        e.getEntity().performCommand(this.plugin.getConfig().getString("onDied.value"));
                    } catch (Exception ignored) {}
                }
                default -> {}
            }
        }
    }

}
