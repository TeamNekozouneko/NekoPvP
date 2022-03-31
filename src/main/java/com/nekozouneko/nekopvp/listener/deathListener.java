package com.nekozouneko.nekopvp.listener;

import com.nekozouneko.nekopvp.NekoPvP;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;

public class deathListener implements Listener {

    private NekoPvP plugin;
    public deathListener() {
        plugin = NekoPvP.getInstance();
    }

    @EventHandler
    public void onKilled(PlayerDeathEvent e) {
        // /scoreboardで管理できるスコアボードにアクセス
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

        // 死亡したときをトリガーするか?
        if (this.plugin.getConfig().getBoolean("onDied.enabled")) {
            // チームが存在するときは
            if (score.getTeam(this.plugin.getConfig().getString("team_one.id")) != null) {
                // チームに死んだプレイヤーが入ってるかつそのチームで死亡したときはが有効なときは跳ねる
                if (score.getTeam(this.plugin.getConfig().getString("team_one.id"))
                        .getEntries().contains(e.getEntity().getName()) &&
                        !this.plugin.getConfig().getBoolean("team_one.onDiedTrigger")) {
                    return;
                }
            }

            // 上記と同じ
            if (score.getTeam(this.plugin.getConfig().getString("team_two.id")) != null) {
                // 上k(ry
                if (score.getTeam(this.plugin.getConfig().getString("team_two.id"))
                        .getEntries().contains(e.getEntity().getName()) &&
                        !this.plugin.getConfig().getBoolean("team_two.onDiedTrigger")) {
                    return;
                }
            }

            // トリガーしたら何を実行するか参照
            switch (this.plugin.getConfig().getString("onDied.triggered")) {
                // ゲームモードなら
                case "GAMEMODE" -> {
                    // ゲームモードでスイッチ
                    switch (this.plugin.getConfig().getString("onDied.value")) {
                        // アドベンチャー
                        case "ADVENTURE", "Adventure", "adventure", "a", "2" -> e.getEntity().setGameMode(GameMode.ADVENTURE);
                        // クリエイティブ
                        case "CREATIVE", "Creative", "creative", "c" , "1" -> e.getEntity().setGameMode(GameMode.CREATIVE);
                        // スペクテイター
                        case "SPECTATOR", "Spectator", "spectator", "sp", "3" -> e.getEntity().setGameMode(GameMode.SPECTATOR);
                        // サバイバル
                        case "SURVIVAL", "Survival", "survival", "s", "0" -> e.getEntity().setGameMode(GameMode.SURVIVAL);
                        // 存在しないゲームモードなら
                        default -> e.getEntity().sendMessage("§cゲームモードの変更に失敗しました。config.ymlを確認してください。");
                    }
                }
                // コマンドなら
                case "COMMAND" -> {
                    // 一応エラー吐いても処理続行させる
                    try {
                        // コマンドを実行
                        e.getEntity().performCommand(this.plugin.getConfig().getString("onDied.value"));
                    } catch (Exception ignored) {}
                }
                default -> e.getEntity().sendMessage("§c未知のトリガーモードです。config.ymlを確認してください。");
            }
        }
    }

}
