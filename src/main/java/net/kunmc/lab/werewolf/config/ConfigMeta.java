package net.kunmc.lab.werewolf.config;

import java.util.List;

public enum ConfigMeta {

    IS_SHOW_ACTIONBAR("アクションバーの表示", "showActionbar", Boolean.class, true),
    STARTUP_COMMANDS("開始コマンド", "startupCommands",List.class, false),
    BASIC_ITEMS("基本アイテム","basicItems", List.class, false),
    SPECIAL_ITEMS("基本アイテム","specialItems", List.class, false);

    public String jName;
    public String path;
    public Class type;
    public boolean isCreateCommand;

    ConfigMeta(String jName, String path, Class type, boolean isCreateCommand) {
        this.jName = jName;
        this.path = path;
        this.type = type;
        this.isCreateCommand = isCreateCommand;
    }
}
