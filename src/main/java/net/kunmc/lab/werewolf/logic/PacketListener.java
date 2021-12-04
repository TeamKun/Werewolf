package net.kunmc.lab.werewolf.logic;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.mojang.authlib.GameProfile;
import net.kunmc.lab.werewolf.Werewolf;
import net.minecraft.server.v1_16_R3.EnumGamemode;
import net.minecraft.server.v1_16_R3.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public class PacketListener {
    public static void addPacketListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Werewolf.plugin, PacketType.Play.Server.PLAYER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                try {
                    Field field = PacketPlayOutPlayerInfo.class.getDeclaredField("b");
                    field.setAccessible(true);

                    // 対象のUUIDを取得
                    UUID target = event.getPlayer().getUniqueId();

                    for (Object playerData : ((List) field.get(packet.getHandle()))) {

                        Field gameProfileField = playerData.getClass().getDeclaredField("d");
                        gameProfileField.setAccessible(true);
                        GameProfile gameProfile = (GameProfile) gameProfileField.get(playerData);

                        Field gameModeField = playerData.getClass().getDeclaredField("c");
                        gameModeField.setAccessible(true);

                        if (target.equals(gameProfile.getId())) {
                            continue;
                        }

                        gameModeField.set(playerData, EnumGamemode.ADVENTURE);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
