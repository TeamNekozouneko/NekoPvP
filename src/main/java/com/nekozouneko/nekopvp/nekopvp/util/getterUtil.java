package com.nekozouneko.nekopvp.nekopvp.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class getterUtil {

    /**
     * 名前のリストからプレイヤーリストへ変換します
     * @param names プレイヤー名のリスト
     * @return プレイヤーに変更したnamesのプレイヤー達
     */
    public static List<Player> getNameToPlayer(List<String> names) {
        List<Player> players = new ArrayList<>();

        for (String PlayerName : names) {
            Player player = Bukkit.getServer().getPlayer(PlayerName);
            if (player == null) {
                continue;
            }
            players.add(player);
        }

        return players;
    }

    /**
     * 名前のリストからプレイヤーリストへ変換します
     * @param names プレイヤー名のリスト
     * @return プレイヤーに変更したnamesのプレイヤー達
     */
    public static List<Player> getNameToPlayer(String[] names) {
        List<Player> players = new ArrayList<>();

        for (String PlayerName : names) {
            Player player = Bukkit.getServer().getPlayer(PlayerName);
            if (player == null) {
                continue;
            }
            players.add(player);
        }

        return players;
    }

    /**
     * 名前のリストからプレイヤーリストへ変換します
     * @param names プレイヤー名のリスト
     * @return プレイヤーに変更したnamesのプレイヤー達
     */
    public static List<Player> getNameToPlayer(java.util.Set<String> names) {
        List<Player> players = new ArrayList<>();

        for (String PlayerName : names) {
            Player player = Bukkit.getServer().getPlayer(PlayerName);
            if (player == null) {
                continue;
            }
            players.add(player);
        }

        return players;
    }

    public static List<String> toPlayerNames(Player[] players) {
        List<String> names = new ArrayList<>();

        for (Player p : players) {
            names.add(p.getName());
        }

        return names;
    }

    public static List<String> toPlayerNames(List<Player> players) {
        List<String> names = new ArrayList<>();

        for (Player p : players) {
            names.add(p.getName());
        }

        return names;
    }

}
