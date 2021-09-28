package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.player.WWPlayerList;
import org.bukkit.entity.Player;

public class GameManager {
    private static WWPlayerList playerList = new WWPlayerList();
    private static boolean isRunning = false;

    /**
     * ゲーム開始
     * */
    public static boolean start() {
        return true;
    }

    /**
     * ゲーム終了
     * */
    public static void end() {
    }

    /**
     * 参加プレイヤー追加
     * */
    public static boolean addPlayer(Player player) {
        return playerList.addPlayer(player);
    }

    /**
     * 参加プレイヤー削除
     * */
    public static boolean removePlayer(Player player) {
        return playerList.removePlayer(player);
    }
}
