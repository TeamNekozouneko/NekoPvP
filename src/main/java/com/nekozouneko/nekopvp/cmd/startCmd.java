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

public class startCmd implements CommandExecutor, TabCompleter {

    private NekoPvP plugin;
    public startCmd() {
        plugin = NekoPvP.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // カウントダウン秒数
        int count = this.plugin.getConfig().getInt("startCmd.countdown");

        // タイマーのインスタンスを作成
        timer t = new timer(count, this.plugin, true);
        // タスク作成
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(this.plugin, t, 0L, 20L);
        // タスクを自らキャンセルできるようにする
        t.setTask(task);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // 空白
        return new ArrayList<>();
    }
}
