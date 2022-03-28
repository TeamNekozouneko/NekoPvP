package com.nekozouneko.nekopvp.nekopvp.cmd;

import com.nekozouneko.nekopvp.nekopvp.NekoPvP;
import com.nekozouneko.nekopvp.nekopvp.util.actionbar;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class teambarCmd implements CommandExecutor, TabCompleter {

    private boolean NowBar = false;
    private NekoPvP plugin = NekoPvP.getInstance();
    private BukkitTask actionTask;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.NowBar) {
            NowBar = false;

            actionTask.cancel();
        } else {
            NowBar = true;
            actionTask = Bukkit.getScheduler().runTaskTimer(this.plugin, new actionbar(), 0L, 10L);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
