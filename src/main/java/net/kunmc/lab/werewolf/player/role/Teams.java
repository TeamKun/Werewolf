package net.kunmc.lab.werewolf.player.role;
/**
 * 勝利条件の判定に使用する値の為狂人は人間陣営とする
 * */
public enum Teams {
    HUMAN("人間"),
    WEREWOLF("人狼");

    public String jName;

    Teams(String jName) {
        this.jName = jName;
    }
}
