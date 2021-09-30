package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.actor.Teams;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {
    /**
     * プレイヤー死亡時の処理
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!GameManager.isIsRunning()) {
            return;
        }

        // プレイヤーの死亡処理
        UUID uuid = event.getEntity().getUniqueId();
        GameManager.deathActor(uuid);
        // 勝敗判定
        Teams winnerTeam = GameManager.winnerTeam();
        // 勝者なし
        if (winnerTeam == null) {
            return;
        }

        // 勝者あり
        // ゲーム終了
        GameManager.end(winnerTeam);
    }
}
