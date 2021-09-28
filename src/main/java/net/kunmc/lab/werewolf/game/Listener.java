package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.player.role.Teams;
import net.kunmc.lab.werewolf.util.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {
    /**
     * プレイヤー死亡時の処理
     * */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!GameManager.isIsRunning()) {
            return;
        }

        // プレイヤーの死亡処理
        UUID uuid = event.getEntity().getUniqueId();
        GameManager.deathActor(uuid);
        MessageUtil.broadcast("1");
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
