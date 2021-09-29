package net.kunmc.lab.werewolf.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class MessageUtil {

    /**
     * 全員にタイトルを表示
     */
    public static void sendTitleAll(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        });
    }

    /**
     * タイトルをクリア
     */
    public static void clearTitle() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle("", "", 0, 0, 0);
        });
    }

    /**
     * 全員にメッセージを表示
     */
    public static void broadcast(String message) {
        Bukkit.broadcast(Component.text(message));
    }

    public static void log(String message) {
        Bukkit.getLogger().info(message);
    }
}
