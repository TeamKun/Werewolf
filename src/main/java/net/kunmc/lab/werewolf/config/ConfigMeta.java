package net.kunmc.lab.werewolf.config;

import java.util.List;

public enum ConfigMeta {

    IS_SHOW_ACTIONBAR("アクションバーの表示", "showActionbar", Boolean.class),
    STARTUP_COMMANDS("開始コマンド", "startupCommands",List.class),
    BASIC_ITEMS("基本アイテム","basicItems", List.class),
    SPECIAL_ITEMS("基本アイテム","specialItems", List.class);

    public String jName;
    public String path;
    public Class type;

    ConfigMeta(String jName, String path, Class type) {
        this.jName = jName;
        this.path = path;
        this.type = type;
    }
}
