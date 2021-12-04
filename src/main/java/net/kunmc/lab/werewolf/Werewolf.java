package net.kunmc.lab.werewolf;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import net.kunmc.lab.werewolf.command.CommandBuilder;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.logic.GameTask;
import net.kunmc.lab.werewolf.logic.Listener;
import net.kunmc.lab.werewolf.logic.PacketListener;
import net.minecraft.server.v1_16_R3.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

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

        PacketListener.addPacketListener();

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE,
                            new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.d, p.getGameMode().getValue()));

                    sendPacket(p, packet);
                });

                Bukkit.getOnlinePlayers().forEach(p -> {
                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_INFO,
                            new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_GAME_MODE, ((CraftPlayer) p).getHandle()));

                    sendPacket(p, packet);
                });
            }

            private void sendPacket(Player target, PacketContainer packet) {
                try {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(target, packet);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(this, 0, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
