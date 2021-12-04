package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.item.ItemType;
import net.kunmc.lab.werewolf.item.Items;
import net.kunmc.lab.werewolf.player.TeamMeta;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {
    /**
     * プレイヤー死亡時の処理
     */
    @EventHandler()
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!GameLogic.isRunning()) {
            return;
        }

        // プレイヤーの死亡処理
        UUID uuid = event.getEntity().getUniqueId();
        GameLogic.deathActor(uuid);
        event.getEntity().setGameMode(GameMode.SPECTATOR);
        // 勝敗判定
        TeamMeta winnerTeam = GameLogic.winnerTeam();

        event.setCancelled(true);
        // 勝者なし
        if (winnerTeam == null) {
            return;
        }
        // 勝者あり
        // ゲーム終了
        GameLogic.end(winnerTeam);
    }

    /**
     * アイテム使用処理
     */
    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!GameLogic.isRunning()) {
            return;
        }

        if (event == null) {
            return;
        }

        if (event.getHand() == null) {
            return;
        }

        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }

        TextComponent textComponent = (TextComponent) item.getItemMeta().displayName();

        if (textComponent == null) {
            return;
        }
        String itemDisplayName = textComponent.content();

        if (Items.use(itemDisplayName, event.getPlayer(), ItemType.NORMAL)) {
            event.getItem().add(-1);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (!GameLogic.isRunning()) {
            return;
        }

        TextComponent textComponent = (TextComponent) event.getItem().getItemMeta().displayName();
        if (textComponent == null) {
            return;
        }

        String itemDisplayName = textComponent.content();

        Items.use(itemDisplayName, event.getPlayer(), ItemType.PORTION);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
    }
}
