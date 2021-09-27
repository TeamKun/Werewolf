package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.player.WWPlayerList;

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
}
