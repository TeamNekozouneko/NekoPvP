package com.nekozouneko.nekopvp.nekopvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class timer implements Runnable {


    private long second;
    private JavaPlugin plugin;
    private BukkitTask task;
    public timer(long second, JavaPlugin plugin) {
        this.second = second;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (second <= 0) {
            for (Player p:Bukkit.getOnlinePlayers()) {
                p.sendTitle(" ", this.plugin.getConfig().getString("translate.start"), 5, 60, 5);
                p.playSound(p, Sound.BLOCK_ANVIL_DESTROY, 1L,1L);
            }
            task.cancel();
        } else {
            for (Player p:Bukkit.getOnlinePlayers()) {
                p.sendTitle(" ", String.valueOf(second), 0, 60, 0);
                p.playSound(p, Sound.UI_BUTTON_CLICK, 1L,1L);
            }
        }

        second--;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

}
