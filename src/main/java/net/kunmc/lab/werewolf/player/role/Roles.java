package net.kunmc.lab.werewolf.player.role;

public enum Roles {
    CITIZEN("市民",0,0, false),
    WEREWOLF("人狼",1,0, true),
    SEER("預言者",0,1, false),
    MEDIUM("霊媒師", 0,1, false),
    MADMAN("狂人", 0,0, false);

    String jName;
    int numberOfPeopleMin;
    int numberOfAbilitiesMin;
    boolean isInhuman;

    Roles(String jName, int numberOfPeopleMin, int numberOfAbilitiesMin, boolean isInhuman) {
        this.jName = jName;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.isInhuman = isInhuman;
    }

    public String jName() {
        return jName;
    }

    public int numberOfPeopleMin() {
        return numberOfPeopleMin;
    }

    public int numberOfAbilitiesMin() {
        return numberOfAbilitiesMin;
    }

    public boolean isInhuman() { return isInhuman; }
}
