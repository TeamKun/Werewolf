package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.player.role.*;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public enum Roles {
    CITIZEN("市民", Teams.HUMAN, new Citizen(),0,0, false, false, "特殊能力なし"),
    WEREWOLF("人狼",Teams.WEREWOLF, new Werewolf(), 1,0, true, false, "/wc <text> - 人狼チャット"),
    SEER("預言者",Teams.HUMAN, new Seer(),0,1, false, true, "/f <player> - 対象を占う"),
    MEDIUM("霊媒師",Teams.HUMAN, new Medium(),0,1, false,true, "/sp <player> - 対象を霊視する"),
    MADMAN("狂人",Teams.HUMAN, new Madman(),0,0, false, false, "特殊能力なし");

    public String jName;
    public Teams team;
    private RoleBuilder builder;
    public int numberOfPeopleMin;
    public int numberOfAbilitiesMin;
    public boolean isInhuman;
    public boolean haveAbility;
    public String abilityDescription;

    Roles(String jName, Teams team, RoleBuilder builder, int numberOfPeopleMin, int numberOfAbilitiesMin, boolean isInhuman, boolean haveAbility, String abilityDescription) {
        this.jName = jName;
        this.team = team;
        this.builder = builder;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.isInhuman = isInhuman;
        this.haveAbility = haveAbility;
        this.abilityDescription = abilityDescription;
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

    public Component actionBarMessage() {
        String message = "";
        if (team.equals(Teams.HUMAN)) {
            message += DecorationConst.GREEN + jName;
        }

        if (team.equals(Teams.WEREWOLF) || this.equals(Roles.MADMAN)) {
            message += DecorationConst.RED + jName;
        }

        message += DecorationConst.RESET + " : " + this.abilityDescription;

        return Component.text(message);
    }

}
