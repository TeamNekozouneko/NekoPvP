package com.nekozouneko.nekopvp.cmd;

import com.nekozouneko.nekopvp.NekoPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class mainCmd implements CommandExecutor, TabCompleter {
    private NekoPvP plugin = NekoPvP.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            this.plugin.reloadConfig();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
