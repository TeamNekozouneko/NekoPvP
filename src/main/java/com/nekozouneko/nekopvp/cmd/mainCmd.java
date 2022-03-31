package com.nekozouneko.nekopvp.cmd;

import com.nekozouneko.nekopvp.NekoPvP;
import com.nekozouneko.nekopvp.util.getterUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class mainCmd implements CommandExecutor, TabCompleter {
    private NekoPvP plugin = NekoPvP.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /np reloadなら
        if (args[0].equalsIgnoreCase("reload")) {
            this.plugin.reloadConfig();
            sender.sendMessage(getterUtil.toColorCode("&",this.plugin.getConfig().getString("translate.prefix"))+getterUtil.toColorCode("&", this.plugin.getConfig().getString("translate.reloaded")));
        } else {
            // それ以外
            sender.sendMessage("§c未実装です。");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // 空白にしとく
        return new ArrayList<>();
    }
}
