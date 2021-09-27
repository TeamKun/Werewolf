package net.kunmc.lab.werewolf.config;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.player.role.Roles;
import org.bukkit.configuration.file.FileConfiguration;

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
    private static void setConfig(String path, Object value) {
        config.set(path, value);
        Werewolf.plugin.saveConfig();
        loadConfig();
    }

    /**
     * config show に表示する文字列を取得する
     * */
    public static StringBuilder showConfig() {
        StringBuilder msg = new StringBuilder();
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");
        msg.append("--人数\n");
        msg.append(Roles.WEREWOLF.jName() + ": " + werewolf.people() + "人\n");
        msg.append(Roles.SEER.jName() + ": " + seer.people() + "人\n");
        msg.append(Roles.MEDIUM.jName() + ": " + medium.people() + "人\n");
        msg.append(Roles.MADMAN.jName() + ": " + madman.people() + "人\n");
        msg.append("--能力の使用回数\n");
        msg.append(Roles.SEER.jName() + ": " + seer.ability() + "回\n");
        msg.append(Roles.MEDIUM.jName() + ": " + medium.ability() + "回\n");
        msg.append("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇現在の設定◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇\n");

        return msg;
    }
}
