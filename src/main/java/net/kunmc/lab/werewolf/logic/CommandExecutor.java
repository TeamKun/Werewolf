package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigMeta;
import net.kunmc.lab.werewolf.player.ActorList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandExecutor {
    private static void execute(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
    }

    static void executeStartupCommands() {
        List<String> commands = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.STARTUP_COMMANDS);
        for (String command : commands) {
            execute(command);
        }
    }

    static void giveBasicItem(ActorList actorList) {
        List<String> basicItemList = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.BASIC_ITEMS);
        for (Player player : actorList.getPlayerList()) {
            for (String basicItem : basicItemList) {
                execute("give " + player.getName() + " " + basicItem);
            }
        }
    }
}
