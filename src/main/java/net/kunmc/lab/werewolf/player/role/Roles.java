package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;

import java.util.UUID;

public enum Roles {
    CITIZEN("市民",Teams.HUMAN, new Citizen(),0,0, false, false),
    WEREWOLF("人狼",Teams.WEREWOLF,new Werewolf(), 1,0, true, false),
    SEER("預言者",Teams.HUMAN,new Seer(),0,1, false, true),
    MEDIUM("霊媒師",Teams.HUMAN, new Medium(),0,1, false,true),
    MADMAN("狂人",Teams.HUMAN, new Madman(),0,0, false, false);

    public String jName;
    public Teams team;
    private RoleBuilder builder;
    public int numberOfPeopleMin;
    public int numberOfAbilitiesMin;
    public boolean isInhuman;
    public boolean haveAbility;

    Roles(String jName, Teams team, RoleBuilder builder, int numberOfPeopleMin, int numberOfAbilitiesMin, boolean isInhuman, boolean haveAbility) {
        this.jName = jName;
        this.team = team;
        this.builder = builder;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.isInhuman = isInhuman;
        this.haveAbility = haveAbility;
    }

    public Actor instance(UUID uuid) {
        return builder.instance(uuid);
    }

    public int numberOfPeople(int value) {
        if (this.numberOfPeopleMin > value) {
            return this.numberOfPeopleMin;
        }
        return value;
    }

    public int numberOfAbilities(int value) {
        if (this.numberOfAbilitiesMin > value) {
            return this.numberOfAbilitiesMin;
        }
        return value;
    }

}
