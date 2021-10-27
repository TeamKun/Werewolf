package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigMeta;
import net.kunmc.lab.werewolf.player.ActorList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CommandExecutor {
    private static void execute(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
    }
    private static void give(Player player, String item) {
        execute("give " + player.getName() + " " + item);
    };

    static void executeStartupCommands() {
        List<String> commands = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.STARTUP_COMMANDS);
        for (String command : commands) {
            execute(command);
        }
    }

    static void giveBasicItem(ActorList actorList) {
        /** アイテムのリスト */
        List<String> basicItemList = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.BASIC_ITEMS);
        for (Player player : actorList.getPlayerList()) {
            for (String basicItem : basicItemList) {
                give(player, basicItem);
            }
        }
    }

    public static void giveSpecialItem(ActorList actorList) {
        /** アイテムのリスト */
        List<String> specialItemList = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.SPECIAL_ITEMS);
        // リストをシャッフル
        Collections.shuffle(specialItemList);

        /** プレイヤーのリスト */
        List<Player> playerList = actorList.getPlayerList();

        /** 人数 */
        int playerCount = playerList.size();
        /** 用意されたアイテムの数 */
        int itemCount = specialItemList.size();

        // アイテムの数を合わせる
        if (playerCount > itemCount) {
            for (int i = 0; i < playerCount - itemCount; i++) {
                specialItemList.add(specialItemList.get(i));
            }
        }

        // アイテム配布処理
        for (int i = 0; i < playerCount; i++) {
            give(playerList.get(i), specialItemList.get(i));
        }
    }
}
