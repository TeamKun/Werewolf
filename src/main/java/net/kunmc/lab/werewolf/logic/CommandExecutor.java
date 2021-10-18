package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigMeta;
import net.kunmc.lab.werewolf.util.MessageUtil;
import org.bukkit.Bukkit;

import java.util.List;

public class CommandExecutor {
    private static void execute(String command) {
        MessageUtil.broadcast(command);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
    }

    static void executeStartupCommands() {
        List<String> commands = (List<String>)ConfigManager.getOthersConfig(ConfigMeta.STARTUP_COMMANDS);
        for (String command : commands) {
            execute(command);
        }
    }
}
