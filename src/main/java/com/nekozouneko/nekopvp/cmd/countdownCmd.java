package com.nekozouneko.nekopvp.cmd;

import com.nekozouneko.nekopvp.NekoPvP;
import com.nekozouneko.nekopvp.util.timer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class countdownCmd implements CommandExecutor, TabCompleter {

    private NekoPvP plugin;
    public countdownCmd() {
        plugin = NekoPvP.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int count;
        if (args.length == 0) {
            count = 10;
        } else {
            count = Integer.parseInt(args[0]);
        }

        timer t = new timer(count, this.plugin, false);
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(this.plugin, t, 0L, 20L);
        t.setTask(task);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(args[0] + "0", args[0] + "1",
                    args[0] + "2", args[0] + "3",
                    args[0] + "4", args[0] + "5",
                    args[0] + "6", args[0] + "7",
                    args[0] + "8", args[0] + "9");
        } else {
            return new ArrayList<>();
        }
    }
}
