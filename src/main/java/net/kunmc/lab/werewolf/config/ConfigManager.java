package net.kunmc.lab.werewolf.config;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.player.RoleMeta;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    /**
     * コンフィグオブジェクト
     */
    static FileConfiguration config;

    /**
     * コンフィグをロードする
     */
    public static void loadConfig(boolean isReload) {
        Werewolf.plugin.saveDefaultConfig();

        if (isReload) {
            Werewolf.plugin.reloadConfig();
        }
        //　コンフィグファイルを取得
        config = Werewolf.plugin.getConfig();
    }

    /**
     * コンフィグをセットする
     */
    public static void setConfig(String path, Object value) {
        config.set(path, value);
        Werewolf.plugin.saveConfig();
        loadConfig(false);
    }

    /**
     * config show role に表示する文字列を取得する
     */
    public static StringBuilder showRoleConfig() {
        List<RoleConfig> list = roleConfigList();
        StringBuilder msg = new StringBuilder();
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");
        msg.append("--人数\n");
        list.forEach(roleConfig -> {
            msg.append(roleConfig.role().jName + ": " + roleConfig.people() + "人\n");
        });
        msg.append("--能力の使用回数\n");
        list.forEach(roleConfig -> {
            if (roleConfig.role().isSpecial) {
                msg.append(roleConfig.role().jName + ": " + roleConfig.ability() + "回\n");
            }
        });
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");

        return msg;
    }

    /**
     * config show others に表示する文字列を取得する
     */
    public static StringBuilder showOthersConfig() {
        StringBuilder msg = new StringBuilder();
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");
        for (ConfigMeta value : ConfigMeta.values()) {
            if (value.isCreateCommand) {
                msg.append(value.jName + " : " + getOthersConfig(value) + "\n");
            }
        }
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇参加者リスト◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");
        List<Player> playerList = GameLogic.actorList.getPlannedPlayerList();
        for (Player player : playerList) {
            msg.append(player.getName() + "\n");
        }
        return msg;
    }

    /**
     * 役職のコンフィグを取得する.
     */
    public static RoleConfig roleConfig(RoleMeta roleMeta) {
        return new RoleConfig(roleMeta);
    }

    /**
     * 役職設定をリストにして返す
     */
    public static List<RoleConfig> roleConfigList() {
        List<RoleConfig> list = new ArrayList<>();
        for (RoleMeta roleMeta : RoleMeta.values()) {
            if (roleMeta.equals(RoleMeta.CITIZEN)) {
                continue;
            }
            list.add(roleConfig(roleMeta));
        }
        return list;
    }

    /**
     * その他のコンフィグを取得する
     * */
    public static Object getOthersConfig(ConfigMeta othersConfig) {
        return config.getObject(othersConfig.path, othersConfig.type);
    }
}
