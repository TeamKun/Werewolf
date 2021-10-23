package net.kunmc.lab.werewolf;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.werewolf.command.CommandBuilder;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.logic.Listener;
import net.kunmc.lab.werewolf.logic.GameTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class Werewolf extends JavaPlugin {
    public static Werewolf plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // コンフィグ読み込み
        ConfigManager.loadConfig(false);

        // コマンド読み込み
        CommandBuilder.buildMainCommand(this);

        // イベント読み込み
        getServer().getPluginManager().registerEvents(new Listener(), this);

        // GUI
        new GameTask().runTaskTimer(plugin, 0, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
