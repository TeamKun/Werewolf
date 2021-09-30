package net.kunmc.lab.werewolf.config;

public enum OthersConfig {

    IS_SHOW_ACTIONBAR("アクションバーの表示", "showActionbar", Boolean.class);

    public String jName;
    public String path;
    public Class type;

    OthersConfig(String jName, String path, Class type) {
        this.jName = jName;
        this.path = path;
        this.type = type;
    }
}
