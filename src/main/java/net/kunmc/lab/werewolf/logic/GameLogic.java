package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.player.AbilityAction;
import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.ActorList;
import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.player.TeamMeta;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kunmc.lab.werewolf.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public class GameLogic implements Listener {
    public static ActorList actorList = new ActorList();
    private static boolean isRunning = false;

    /**
     * ゲーム開始
     */
    public static boolean start() {
        // 配役処理
        if (!actorList.setActors()) {
            return false;
        }

        // プレイヤーに対する処理
        actorList.setup();
        // スタートアップコマンド実行
        CommandExecutor.executeStartupCommands();
        // 基本アイテムの付与
        CommandExecutor.giveBasicItem(actorList);
        // 特殊アイテムの付与
        CommandExecutor.giveSpecialItem(actorList);

        isRunning = true;
        return true;
    }

    /**
     * ゲーム終了
     */
    public static void end(TeamMeta winnerTeam) {
        MessageUtil.broadcast(DecorationConst.GREEN + winnerTeam.jName + "の勝利！");
        actorList.gameSet();
        isRunning = false;
    }

    /**
     * ゲーム強制終了
     */
    public static boolean stop() {
        if (!isRunning) {
            return false;
        }

        actorList.gameSet();
        isRunning = false;
        return true;
    }

    /**
     * ゲームが実行中か
     */
    public static boolean isRunning() {
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
    public static TeamMeta winnerTeam() {
        return actorList.winnerTeam();
    }

    /**
     * 能力を使用する
     */
    public static CommandResult useAbilities(CommandSender sender,
                                             AbilityArgument arg,
                                             AbilityAction action) {

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
        return actor.useAbility(arg, action);
    }

    /**
     * UUIDから参加者を取得する
     */
    public static Actor getActor(UUID uuid) {
        return actorList.getActor(uuid);
    }

    /**
     * 人狼のリストを取得する.
     */
    public static List<Actor> getWerewolfList() {
        return actorList.werewolfList();
    }

    /**
     * 参加者のリストを取得する
     * */
    public static List<Player> getPlayerList() {
        return actorList.getPlayerList();
    }

}
