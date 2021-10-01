package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.RoleConfig;
import net.kunmc.lab.werewolf.meta.RoleMeta;
import net.kunmc.lab.werewolf.meta.TeamMeta;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class ActorList {
    private Set<UUID> playerSet = new HashSet<>();
    private Set<Actor> actors = new HashSet<>();

    /**
     * プレイヤーを追加する
     */
    public boolean addPlayer(Player player) {
        return playerSet.add(player.getUniqueId());
    }

    /**
     * プレイヤーを削除する
     */
    public boolean removePlayer(Player player) {
        return playerSet.remove(player.getUniqueId());
    }

    /**
     * 参加者のリストを取得する
     * */
    public List<Player> getPlayerList() {
        List<Player> result = new ArrayList<>();
        actors.forEach(actor -> {
            result.add(Bukkit.getPlayer(actor.uuid()));
        });

        return result;
    }

    /**
     * プレイヤーが死亡したときの処理
     */
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
     */
    public boolean setActors() {

        // 参加者をアドベンチャーモードに変更
        for (UUID uuid : playerSet) {
            Player player = Bukkit.getPlayer(uuid);

            if (player != null) {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }

        // TODO 基本アイテムの付与
        // TODO 特殊アイテムの付与

        // 配役処理
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
            actors.add(RoleMeta.CITIZEN.instance(uuid));
        }

        this.playerSet = tmpSet;

        return true;
    }

    /**
     * 勝利した陣営を取得する
     */
    public TeamMeta winnerTeam() {
        int humanTeamCount = 0;
        int werewolfTeamCount = 0;
        for (Actor actor : this.actors) {
            if (actor.team().equals(TeamMeta.HUMAN) && !actor.isDead()) {
                humanTeamCount++;
            }

            if (actor.team().equals(TeamMeta.WEREWOLF) && !actor.isDead()) {
                werewolfTeamCount++;
            }
        }

        // 人間が0の時
        if (humanTeamCount == 0) {
            return TeamMeta.WEREWOLF;
        }

        // 人狼が0の時
        if (werewolfTeamCount == 0) {
            return TeamMeta.HUMAN;
        }

        return null;
    }

    /**
     * アクションバーを表示する
     */
    public void showActionBar() {
        actors.forEach(actor -> {
            actor.showActionBar();
        });
    }

    /**
     * UUIDから参加者を取得する
     */
    public Actor getActor(UUID uuid) {
        for (Actor actor : actors) {
            if (actor.uuid().equals(uuid)) {
                return actor;
            }
        }

        return null;
    }

    /**
     * 人狼のリストを取得する
     */
    public List<Actor> werewolfList() {
        List<Actor> result = new ArrayList<>();
        actors.forEach(actor -> {
            if (actor.isWerewolf()) {
                result.add(actor);
            }
        });

        return result;
    }
}
