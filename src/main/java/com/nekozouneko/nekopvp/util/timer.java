package com.nekozouneko.nekopvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

public class timer implements Runnable {

    // 初期変数を宣言
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
        // 0秒以下なら
        if (this.second <= 0) {
            // /startによる起動なら
            if (this.isGame) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    // 全員にタイトルと音声を再生
                    p.sendTitle(" ", this.plugin.getConfig().getString("translate.start"), 5, 60, 5);
                    p.playSound(p, Sound.BLOCK_ANVIL_DESTROY, 1L, 1L);
                }

                // /scoreboardで管理できるスコアボードを取得
                Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

                // チーム1でテレポートが有効化されてるか
                if (this.plugin.getConfig().getBoolean("team_one.teleport") && score.getTeam(this.plugin.getConfig().getString("team_one.id")) != null) {
                    for (Player p : getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_one.id")).getEntries())) {
                        p.teleport(new Location(p.getWorld(), this.plugin.getConfig().getDouble("team_one.x"), this.plugin.getConfig().getDouble("team_one.y"), this.plugin.getConfig().getDouble("team_one.z")));
                    }
                }

                // チーム2でテレポートが有効化されてるか
                if (this.plugin.getConfig().getBoolean("team_two.teleport") && score.getTeam(this.plugin.getConfig().getString("team_two.id")) != null) {
                    for (Player p : getterUtil.getNameToPlayer(score.getTeam(this.plugin.getConfig().getString("team_two.id")).getEntries())) {
                        p.teleport(new Location(p.getWorld(), this.plugin.getConfig().getDouble("team_two.x"), this.plugin.getConfig().getDouble("team_two.y"), this.plugin.getConfig().getDouble("team_two.z")));
                    }
                }

                // チーム1でコマンド実行が有効化されてるなら
                if (this.plugin.getConfig().getBoolean("team_one.enable_cmd")) {
                    for (String cmd : this.plugin.getConfig().getStringList("team_one.commands")) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    }
                }

                // チーム2でコマンド実行が有効化されてるなら
                if (this.plugin.getConfig().getBoolean("team_two.enable_cmd")) {
                    for (String cmd : this.plugin.getConfig().getStringList("team_two.commands")) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    }
                }

                // タスク止める
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
                // 全員にカウントダウンを送信
                p.sendTitle(" ", String.valueOf(second), 0, 60, 0);
                p.playSound(p, Sound.UI_BUTTON_CLICK, 1L,1L);
            }
        }

        // secondから1秒引く
        this.second--;
    }

    // キャンセル可能にする関数
    public void setTask(BukkitTask task) {
        // タスクをセット
        this.task = task;
    }

}
