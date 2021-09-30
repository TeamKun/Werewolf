package net.kunmc.lab.werewolf.meta;

public enum ConfigMeta {

    IS_SHOW_ACTIONBAR("アクションバーの表示", "showActionbar", Boolean.class);

    public String jName;
    public String path;
    public Class type;

    ConfigMeta(String jName, String path, Class type) {
        this.jName = jName;
        this.path = path;
        this.type = type;
    }
}
