package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.player.TeamMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {
    /**
     * プレイヤー死亡時の処理
     */
    @EventHandler(ignoreCancelled = true)
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
}
