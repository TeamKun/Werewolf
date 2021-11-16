package net.kunmc.lab.werewolf.item;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.util.Second;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class InvisiblePotion implements SpecialItem {
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public void use(Player user) {
        Second duration = new Second(20);
        new GlowingTask(duration, user).runTaskTimerAsynchronously(Werewolf.plugin, 0, 5);
    }


    /**
     * 発酵処理用インナークラス
     */
    private class GlowingTask extends BukkitRunnable {
        private int startTick;
        private Second duration;
        private Player user;

        public GlowingTask(Second duration, Player user) {
            this.duration = duration;
            this.user = user;
            this.startTick = Bukkit.getCurrentTick();
        }

        @Override
        public void run() {
            int elapsedTick = Bukkit.getCurrentTick() - this.startTick;
            List<Player> targetList = GameLogic.actorList.getPlayerList(user.getUniqueId(), false);

            // 時間切れ
            if (elapsedTick > this.duration.tick()) {
                targetList.forEach(target -> {
                    setGlowing(user, target, false);
                });
                this.cancel();
                return;
            }

            targetList.forEach(target -> {
                setGlowing(user, target, true);
            });
        }

        private void setGlowing(Player sender, Player target, boolean b) {
            PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
            packetContainer.getIntegers().write(0, target.getEntityId());
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
            watcher.setEntity(target);
            watcher.setObject(0, serializer, (byte) (b ? 0x40 : 0x00));
            packetContainer.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
            try {
                protocolManager.sendServerPacket(sender, packetContainer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
