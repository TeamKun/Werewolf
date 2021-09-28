package net.kunmc.lab.werewolf.config;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.player.Roles;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    /** コンフィグオブジェクト */
    static FileConfiguration config;

    private static RoleConfig werewolf;
    private static RoleConfig seer;
    private static RoleConfig medium;
    private static RoleConfig madman;

    /**
     * コンフィグをロードする
     * */
    public static void loadConfig() {
        Werewolf.plugin.saveDefaultConfig();

        //　コンフィグファイルを取得
        config = Werewolf.plugin.getConfig();

        werewolf = new RoleConfig(Roles.WEREWOLF, config.getInt(ConfigPathConst.PEOPLE_WEREWOLF), 0);
        seer = new RoleConfig(Roles.SEER, config.getInt(ConfigPathConst.PEOPLE_SEER), config.getInt(ConfigPathConst.ABILITY_SEER));
        medium = new RoleConfig(Roles.MEDIUM, config.getInt(ConfigPathConst.PEOPLE_MEDIUM), config.getInt(ConfigPathConst.ABILITY_MEDIUM));
        madman = new RoleConfig(Roles.MADMAN, config.getInt(ConfigPathConst.PEOPLE_MADMAN), 0);
    }

    /**
     * コンフィグをセットする
     * */
    public static void setConfig(String path, Object value) {
        config.set(path, value);
        Werewolf.plugin.saveConfig();
        loadConfig();
    }

    /**
     * config show に表示する文字列を取得する
     * */
    public static StringBuilder showConfig() {
        List<RoleConfig> list = roleConfigList();
        StringBuilder msg = new StringBuilder();
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");
        msg.append("--人数\n");
        list.forEach(roleConfig -> {
            msg.append(roleConfig.role().jName + ": " + roleConfig.people() + "人\n");
        });
        msg.append("--能力の使用回数\n");
        list.forEach(roleConfig -> {
            if (roleConfig.role().haveAbility) {
                msg.append(roleConfig.role().jName + ": " + roleConfig.ability() + "回\n");
            }
        });
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");

        return msg;
    }

    /**
     * 役職設定をリストにして返す
     * */
    public static List<RoleConfig> roleConfigList() {
        try {
            List<RoleConfig> list = new ArrayList<>();
            for (Field field : ConfigManager.class.getDeclaredFields()) {
                Object value = field.get(null);

                if (!(value instanceof RoleConfig)) {
                    continue;
                }

                list.add((RoleConfig) value);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
