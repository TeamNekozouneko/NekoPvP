package com.nekozouneko.nekopvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

public class timer implements Runnable {


    private long second;
    private JavaPlugin plugin;
    private BukkitTask task;
    private Boolean isGame;
    public timer(long second, JavaPlugin plugin, Boolean isGame) {
        this.second = second;
        this.plugin = plugin;
        this.isGame = isGame;
    }

    @Override
    public void run() {
        if (this.second <= 0) {
            if (this.isGame) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(" ", this.plugin.getConfig().getString("translate.start"), 5, 60, 5);
                    p.playSound(p, Sound.BLOCK_ANVIL_DESTROY, 1L, 1L);
                }

                Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

                if (this.plugin.getConfig().getBoolean("team_one.teleport") && score.getTeam(this.plugin.getConfig().getString("team_one.id")) != null) {
                    for (Player p : getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_one.id")).getEntries())) {
                        p.teleport(new Location(p.getWorld(), this.plugin.getConfig().getDouble("team_one.x"), this.plugin.getConfig().getDouble("team_one.y"), this.plugin.getConfig().getDouble("team_one.z")));
                    }
                }

                if (this.plugin.getConfig().getBoolean("team_two.teleport") && score.getTeam(this.plugin.getConfig().getString("team_two.id")) != null) {
                    for (Player p : getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_two.id")).getEntries())) {
                        p.teleport(new Location(p.getWorld(), this.plugin.getConfig().getDouble("team_two.x"), this.plugin.getConfig().getDouble("team_two.y"), this.plugin.getConfig().getDouble("team_two.z")));
                    }
                }

                if (this.plugin.getConfig().getBoolean("team_one.enable_cmd")) {
                    for (String cmd : this.plugin.getConfig().getStringList("team_one.commands")) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    }
                }

                if (this.plugin.getConfig().getBoolean("team_two.enable_cmd")) {
                    for (String cmd : this.plugin.getConfig().getStringList("team_two.commands")) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    }
                }

                this.task.cancel();
            } else {
                for (Player p:Bukkit.getOnlinePlayers()) {
                    p.sendTitle(" ", String.valueOf(second), 0, 60, 5);
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 1L,1L);
                }
                this.task.cancel();
            }
        } else {
            for (Player p:Bukkit.getOnlinePlayers()) {
                p.sendTitle(" ", String.valueOf(second), 0, 60, 0);
                p.playSound(p, Sound.UI_BUTTON_CLICK, 1L,1L);
            }
        }

        this.second--;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

}
