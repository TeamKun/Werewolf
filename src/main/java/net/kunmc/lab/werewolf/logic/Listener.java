package net.kunmc.lab.werewolf.logic;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import net.kunmc.lab.werewolf.item.ItemType;
import net.kunmc.lab.werewolf.item.Items;
import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.TeamMeta;
import net.kunmc.lab.werewolf.util.MessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.awt.*;
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
        // 勝敗判定
        TeamMeta winnerTeam = GameLogic.winnerTeam();
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
     * */
    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!GameLogic.isRunning()) {
            return;
        }

        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) ) {
            return;
        }

        String itemDisplayName = ((TextComponent) event.getItem().getItemMeta().displayName()).content();
        if (Items.use(itemDisplayName, event.getPlayer(), ItemType.NORMAL)) {
            event.getItem().add(-1);
        };
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (!GameLogic.isRunning()) {
            return;
        }

        String itemDisplayName = ((TextComponent) event.getItem().getItemMeta().displayName()).content();

        Items.use(itemDisplayName, event.getPlayer(), ItemType.PORTION);
    }

}
