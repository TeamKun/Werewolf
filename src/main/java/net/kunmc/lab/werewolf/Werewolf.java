package net.kunmc.lab.werewolf;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.werewolf.command.Main;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.game.Listener;
import net.kunmc.lab.werewolf.game.UserInterface;
import org.bukkit.plugin.java.JavaPlugin;

public final class Werewolf extends JavaPlugin {
    public static Werewolf plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // コンフィグ読み込み
        ConfigManager.loadConfig();
        // コマンド読み込み
        FlyLib.create(this, builder -> {
            builder.command(new Main());
        });

        // イベント読み込み
        getServer().getPluginManager().registerEvents(new Listener(), this);

        // GUI
        new UserInterface().runTaskTimer(plugin, 0, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
