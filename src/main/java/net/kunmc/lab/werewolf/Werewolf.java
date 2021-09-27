package net.kunmc.lab.werewolf;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.werewolf.command.Main;
import net.kunmc.lab.werewolf.config.ConfigManager;
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
