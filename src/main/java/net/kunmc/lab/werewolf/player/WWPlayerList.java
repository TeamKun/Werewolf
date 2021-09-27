package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import net.kunmc.lab.werewolf.player.role.Citizen;
import net.kunmc.lab.werewolf.player.role.Roles;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class WWPlayerList {
    private List<WWPlayer> list = new ArrayList<>();
    private Class<ConfigManager> configManagerClass;

    /**
     * プレイヤーを追加する
     * */
    public boolean addPlayer(Player player) {
        UUID uuid = player.getUniqueId();

        for (WWPlayer p : list) {
            if (p.uuid().equals(uuid)) {
                list.add(Roles.CITIZEN.instance(uuid));
                return true;
            }
        }
        return false;
    }

    /**
     * プレイヤーを削除する
     * */
    public boolean removePlayer(Player player) {
        UUID uuid = player.getUniqueId();

        for (WWPlayer p : list) {
            if (p.uuid().equals(uuid)) {
                list.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * 配役する
     * */
    public boolean setRole() {
        List<RoleConfig> configList = ConfigManager.roleConfigList();

        List<WWPlayer> newList = new ArrayList<>();

        // 人数チェック
        int minPlayer = 0;
        for (RoleConfig roleConfig : configList) {
            minPlayer += roleConfig.people();
        }
        if (list.size() < minPlayer) {
            return false;
        }

        // 配役処理
        Collections.shuffle(list);
        for (RoleConfig roleConfig : configList) {
            for (int i = 0; i < roleConfig.people(); i++) {
                newList.add(roleConfig.role().instance(list.remove(0).uuid()));
            }
        }
        for (WWPlayer wwPlayer : list) {
            newList.add(wwPlayer);
        }

        return true;
    }
}
