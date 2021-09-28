package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.player.ActorList;
import net.kunmc.lab.werewolf.player.role.Teams;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kunmc.lab.werewolf.util.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class GameManager implements Listener {
    static ActorList actorList = new ActorList();
    private static boolean isRunning = false;

    /**
     * ゲーム開始
     * */
    public static boolean start() {
        if (!actorList.setRole()) {
            return false;
        }
        isRunning = true;
        return true;
    }

    /**
     * ゲーム終了
     * */
    public static void end(Teams winnerTeam) {
        MessageUtil.broadcast(DecorationConst.GREEN + winnerTeam.jName + "の勝利！");
        isRunning = false;
    }

    /**
     * ゲーム強制終了
     * */
    public static boolean stop() {
        if (!isRunning) {
            return false;
        }

        isRunning = false;
        return true;
    }

    /**
     * ゲームが実行中か
     * */
    public static boolean isIsRunning() {
        return isRunning;
    }

    /**
     * 参加プレイヤー追加
     * */
    public static boolean addPlayer(Player player) {
        return actorList.addPlayer(player);
    }

    /**
     * 参加プレイヤー削除
     * */
    public static boolean removePlayer(Player player) {
        return actorList.removePlayer(player);
    }

    /**
     * プレイヤーが死亡したときの処理
     * */
    public static boolean deathActor(UUID uuid) {
        return actorList.death(uuid);
    }

    /**
     * 勝利した陣営を取得する
     * */
    public static Teams winnerTeam() {
        return actorList.winnerTeam();
    }
}
