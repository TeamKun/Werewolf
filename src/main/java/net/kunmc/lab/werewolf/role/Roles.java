package net.kunmc.lab.werewolf.role;

public enum Roles {
    CITIZEN("市民",0,0),
    WEREWOLF("人狼",1,0),
    SEER("預言者",0,1),
    MEDIUM("霊媒師", 0,1),
    MADMAN("狂人", 0,0);

    String jName;
    int numberOfPeopleMin;
    int numberOfAbilitiesMin;

    Roles(String jName, int numberOfPeopleMin, int numberOfAbilitiesMin) {
        this.jName = jName;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
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
}
