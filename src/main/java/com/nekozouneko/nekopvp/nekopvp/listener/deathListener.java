package com.nekozouneko.nekopvp.nekopvp.listener;

import com.nekozouneko.nekopvp.nekopvp.NekoPvP;
import org.bukkit.GameMode;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathListener implements Listener {

    private NekoPvP plugin;
    public deathListener() {
        plugin = NekoPvP.getInstance();
    }

    @EventHandler
    public void onKilled(PlayerDeathEvent e) {
        if (this.plugin.getConfig().getBoolean("onDied.enabled")) {
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
                    } catch (Exception er) {}
                }
                default -> {}
            }
        }
    }

}
