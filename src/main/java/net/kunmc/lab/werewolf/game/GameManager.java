package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.actor.Actor;
import net.kunmc.lab.werewolf.actor.ActorList;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.actor.Teams;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kunmc.lab.werewolf.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class GameManager implements Listener {
    static ActorList actorList = new ActorList();
    private static boolean isRunning = false;

    /**
     * ゲーム開始
     */
    public static boolean start() {
        if (!actorList.setRole()) {
            return false;
        }
        isRunning = true;
        return true;
    }

    /**
     * ゲーム終了
     */
    public static void end(Teams winnerTeam) {
        MessageUtil.broadcast(DecorationConst.GREEN + winnerTeam.jName + "の勝利！");
        isRunning = false;
    }

    /**
     * ゲーム強制終了
     */
    public static boolean stop() {
        if (!isRunning) {
            return false;
        }

        isRunning = false;
        return true;
    }

    /**
     * ゲームが実行中か
     */
    public static boolean isIsRunning() {
        return isRunning;
    }

    /**
     * 参加プレイヤー追加
     */
    public static boolean addPlayer(Player player) {
        return actorList.addPlayer(player);
    }

    /**
     * 参加プレイヤー削除
     */
    public static boolean removePlayer(Player player) {
        return actorList.removePlayer(player);
    }

    /**
     * プレイヤーが死亡したときの処理
     */
    public static boolean deathActor(UUID uuid) {
        return actorList.death(uuid);
    }

    /**
     * 勝利した陣営を取得する
     */
    public static Teams winnerTeam() {
        return actorList.winnerTeam();
    }

    /**
     * 能力を使用する
     */
    public static CommandResult useAbilities(CommandSender sender, RoleMeta role, Object args) {

        if (!isRunning) {
            return new CommandResult(false, "このコマンドは人狼ゲーム進行中のみ使用できます");
        }

        Player user = Bukkit.getPlayer(sender.getName());
        if (user == null) {
            return new CommandResult(false, "このコマンドはプレイヤーのみが使用できます");
        }

        Actor actor = actorList.getActor(user.getUniqueId());

        if (actor == null) {
            return new CommandResult(false, "このコマンドは人狼ゲーム参加者のみが使用できます");
        }

        if (actor.isDead()) {
            return new CommandResult(false, "あなたはすでに死亡しています");
        }

        // 能力を使用
        return actor.useAbilities(role, args);
    }

    /**
     * UUIDから参加者を取得する
     * */
    public static Actor getActor(UUID uuid) {
        return actorList.getActor(uuid);
    }
}
