package net.kunmc.lab.werewolf.player;

/**
 * 勝利条件の判定に使用する値の為狂人は人間陣営とする
 */
public enum TeamMeta {
    HUMAN("人間"),
    WEREWOLF("人狼");

    public String jName;

    TeamMeta(String jName) {
        this.jName = jName;
    }
}
