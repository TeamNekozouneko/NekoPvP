package com.nekozouneko.nekopvp.util;

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

    /**
     * プレイヤーリストからプレイヤー名リストに変換
     * @param players プレイヤー名リスト
     * @return
     */
    public static List<String> toPlayerNames(Player[] players) {
        List<String> names = new ArrayList<>();

        for (Player p : players) {
            names.add(p.getName());
        }

        return names;
    }

    /**
     * プレイヤーリストからプレイヤー名リストに変換
     * @param players プレイヤー名リスト
     * @return
     */
    public static List<String> toPlayerNames(List<Player> players) {
        List<String> names = new ArrayList<>();

        for (Player p : players) {
            names.add(p.getName());
        }

        return names;
    }

    /**
     * cpをカラーコードに変換
     * @param cp §からカラーコードに置き換えするもの
     * @param str テキスト
     * @return
     */
    public static String toColorCode(String cp, String str) {
        return str.replaceAll(cp+"a", "§a").replaceAll(cp+"A", "§a")
                .replaceAll(cp+"b", "§b").replaceAll(cp+"B", "§b")
                .replaceAll(cp+"c", "§c").replaceAll(cp+"C", "§c")
                .replaceAll(cp+"d", "§d").replaceAll(cp+"D", "§d")
                .replaceAll(cp+"e", "§e").replaceAll(cp+"E", "§e")
                .replaceAll(cp+"f", "§f").replaceAll(cp+"F", "§f")
                .replaceAll(cp+"k", "§k").replaceAll(cp+"K", "§k")
                .replaceAll(cp+"l", "§l").replaceAll(cp+"L", "§l")
                .replaceAll(cp+"m", "§m").replaceAll(cp+"M", "§m")
                .replaceAll(cp+"n", "§n").replaceAll(cp+"N", "§n")
                .replaceAll(cp+"o", "§o").replaceAll(cp+"O", "§o")
                .replaceAll(cp+"r", "§r").replaceAll(cp+"R", "§r")
                .replaceAll(cp+"0", "§0").replaceAll(cp+"1", "§1")
                .replaceAll(cp+"2", "§2").replaceAll(cp+"3", "§3")
                .replaceAll(cp+"4", "§4").replaceAll(cp+"5", "§5")
                .replaceAll(cp+"6", "§6").replaceAll(cp+"7", "§7")
                .replaceAll(cp+"8", "§8").replaceAll(cp+"9", "§9");
    }

}
