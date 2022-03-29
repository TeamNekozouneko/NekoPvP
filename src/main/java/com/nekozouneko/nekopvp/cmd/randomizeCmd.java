package com.nekozouneko.nekopvp.cmd;

import com.nekozouneko.nekopvp.NekoPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomizeCmd implements CommandExecutor, TabCompleter {

    private NekoPvP plugin;
    public randomizeCmd() {
        plugin = NekoPvP.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

        String one_name = this.plugin.getConfig().getString("team_one.id");
        Team one = score.getTeam(one_name);

        if (one == null) {
            one = score.registerNewTeam(one_name);
            one.setColor(ChatColor.RED);
        }

        String two_name = this.plugin.getConfig().getString("team_two.id");
        Team two = score.getTeam(two_name);

        if (two == null) {
            two = score.registerNewTeam(two_name);
            two.setColor(ChatColor.BLUE);
        }

        Random r = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(score);
            if (one.getEntries().size() == two.getEntries().size()) {
                int rnd = r.nextInt(2);
                if (rnd == 0) {
                    one.addEntry(p.getName());
                } else {
                    two.addEntry(p.getName());
                }
            } else {
                if (one.getEntries().size() < two.getEntries().size()) {
                    one.addEntry(p.getName());
                } else {
                    two.addEntry(p.getName());
                }
            }
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(" ", "§c"+one.getEntries().size()+" §f- §9"+two.getEntries().size(), 5, 60, 5);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
