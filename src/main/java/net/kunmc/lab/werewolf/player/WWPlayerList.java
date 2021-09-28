package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import net.kunmc.lab.werewolf.player.role.Roles;
import org.bukkit.entity.Player;

import java.util.*;

public class WWPlayerList {
    private Set<UUID> playerSet = new HashSet<>();
    private Set<WWPlayer> actors = new HashSet<>();

    /**
     * プレイヤーを追加する
     * */
    public boolean addPlayer(Player player) {
        return playerSet.add(player.getUniqueId());
    }

    /**
     * プレイヤーを削除する
     * */
    public boolean removePlayer(Player player) {
        return playerSet.remove(player.getUniqueId());
    }

    /**
     * 配役する
     * */
    public boolean setRole() {
        List<RoleConfig> configList = ConfigManager.roleConfigList();

        // 人数チェック
        int minPlayer = 0;
        for (RoleConfig roleConfig : configList) {
            minPlayer += roleConfig.people();
        }
        if (playerSet.size() < minPlayer) {
            return false;
        }

        List<UUID> playerList = new ArrayList<>(playerSet);

        // 配役処理
        Collections.shuffle(playerList);
        for (RoleConfig roleConfig : configList) {
            for (int i = 0; i < roleConfig.people(); i++) {
                actors.add(roleConfig.role().instance(playerList.remove(0)));
            }
        }
        for (UUID uuid : playerList) {
            actors.add(Roles.CITIZEN.instance(uuid));
        }

        return true;
    }
}
