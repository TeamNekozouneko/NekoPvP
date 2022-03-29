package com.nekozouneko.nekopvp.nekopvp.util;

import com.nekozouneko.nekopvp.nekopvp.NekoPvP;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class actionbar implements Runnable{

    private NekoPvP plugin = NekoPvP.getInstance();

    @Override
    public void run() {
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

        String showText = this.plugin.getConfig().getString("translate.actionbar");
        showText = showText.replaceAll("%online%", ""+ Bukkit.getOnlinePlayers().size());

        try {
            showText = showText.replaceAll("%TeamOne_online%", ""+getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_one.id")).getEntries()).size());
        } catch (Exception e) {}

        try {
            showText = showText.replaceAll("%TeamTwo_online%", ""+getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_two.id")).getEntries()).size());
        } catch (Exception e) {}

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(showText));
        }
    }
}
