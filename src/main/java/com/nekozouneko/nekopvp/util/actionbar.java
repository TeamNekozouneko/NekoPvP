package com.nekozouneko.nekopvp.util;

import com.nekozouneko.nekopvp.NekoPvP;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class actionbar implements Runnable{

    private NekoPvP plugin = NekoPvP.getInstance();

    @Override
    public void run() {
        // /scoreboardで管理できるようにする
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

        // アクションバーのテキスト取得
        String showText = this.plugin.getConfig().getString("translate.actionbar");
        // オンライン人数に置き換え
        showText = showText.replaceAll("%online%", ""+ Bukkit.getOnlinePlayers().size());

        // null対策のtry
        try {
            // チーム1のオンライン
            showText = showText.replaceAll("%TeamOne_online%", ""+getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_one.id")).getEntries()).size());
        } catch (Exception ignored) {}

        // null対策のtry
        try {
            // チーム2のオンライン
            showText = showText.replaceAll("%TeamTwo_online%", ""+getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_two.id")).getEntries()).size());
        } catch (Exception ignored) {}

        for (Player p : Bukkit.getOnlinePlayers()) {
            // 全員にアクションバーを送信
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(showText));
        }
    }
}
