package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import org.bukkit.entity.Player;

import java.util.*;

public class ActorList {
    private Set<UUID> playerSet = new HashSet<>();
    private Set<Actor> actors = new HashSet<>();

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
     * プレイヤーが死亡したときの処理
     * */
    public boolean death(UUID uuid) {
        for (Actor actor : actors) {
            if (actor.uuid().equals(uuid)) {
                actor.death();
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
        // 配役をクリア
        actors.clear();

        // 人数チェック
        int minPlayer = 0;
        for (RoleConfig roleConfig : configList) {
            minPlayer += roleConfig.people();
        }
        if (playerSet.size() < minPlayer) {
            return false;
        }

        List<UUID> playerList = new ArrayList<>(playerSet);

        Set<UUID> tmpSet = new HashSet<>();
        // 配役処理
        Collections.shuffle(playerList);
        for (RoleConfig roleConfig : configList) {
            for (int i = 0; i < roleConfig.people(); i++) {
                UUID uuid = playerList.remove(0);
                actors.add(roleConfig.role().instance(uuid));
                tmpSet.add(uuid);
            }
        }
        for (UUID uuid : playerList) {
            tmpSet.add(uuid);
            actors.add(Roles.CITIZEN.instance(uuid));
        }

        this.playerSet = tmpSet;

        return true;
    }

    /**
     * 勝利した陣営を取得する
     * */
    public Teams winnerTeam() {
        int humanTeamCount = 0;
        int werewolfTeamCount = 0;
        for (Actor actor : this.actors) {
            if (actor.team().equals(Teams.HUMAN) && !actor.isDead()) {
                humanTeamCount ++;
            }

            if (actor.team().equals(Teams.WEREWOLF) && !actor.isDead()) {
                werewolfTeamCount ++;
            }
        }

        // 人間が0の時
        if (humanTeamCount == 0) {
            return Teams.WEREWOLF;
        }

        // 人狼が0の時
        if (werewolfTeamCount == 0) {
            return Teams.HUMAN;
        }

        return null;
    }

    /**
     * アクションバーを表示する
     * */
    public void showActionBar() {
        actors.forEach(actor -> {
            actor.showActionBar();
        });
    }

    /**
     * UUIDから参加者を取得する
     * */
    public Actor getActor(UUID uuid) {
        for (Actor actor : actors) {
            if (actor.uuid().equals(uuid)) {
                return actor;
            }
        }

        return null;
    }
}
