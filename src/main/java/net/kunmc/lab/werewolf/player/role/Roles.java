package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;

import java.util.UUID;

public enum Roles {
    CITIZEN("市民",new Citizen(),0,0, false, false),
    WEREWOLF("人狼",new Werewolf(), 1,0, true, false),
    SEER("預言者",new Seer(),0,1, false, true),
    MEDIUM("霊媒師", new Medium(),0,1, false,true),
    MADMAN("狂人", new Madman(),0,0, false, false);

    String jName;
    RoleBuilder builder;
    int numberOfPeopleMin;
    int numberOfAbilitiesMin;
    boolean isInhuman;
    boolean haveAbility;

    Roles(String jName, RoleBuilder builder, int numberOfPeopleMin, int numberOfAbilitiesMin, boolean isInhuman, boolean haveAbility) {
        this.jName = jName;
        this.builder = builder;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.isInhuman = isInhuman;
        this.haveAbility = haveAbility;
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

    public boolean haveAbility() {
        return haveAbility;
    }

    public WWPlayer instance(UUID uuid) {
        return builder.instance(uuid);
    }
}
