package com.nekozouneko.nekopvp.nekopvp.cmd;

import com.nekozouneko.nekopvp.nekopvp.NekoPvP;
import com.nekozouneko.nekopvp.nekopvp.util.timer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class startCmd implements CommandExecutor, TabCompleter {

    private NekoPvP plugin;
    public startCmd() {
        plugin = NekoPvP.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int count = this.plugin.getConfig().getInt("startCmd.countdown");

        timer t = new timer(count, this.plugin);
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(this.plugin, t, 0L, 20L);
        t.setTask(task);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
