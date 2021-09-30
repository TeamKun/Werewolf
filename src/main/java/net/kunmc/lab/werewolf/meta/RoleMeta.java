package net.kunmc.lab.werewolf.meta;

import net.kunmc.lab.werewolf.actor.*;
import net.kunmc.lab.werewolf.util.DecorationConst;

import java.util.UUID;

public enum RoleMeta {
    CITIZEN("市民",
            TeamMeta.HUMAN,
            new Citizen(),
            0,
            0,
            null,
            null,
            false,
            false,
            null,
            "特殊能力なし"),
    WEREWOLF("人狼",
            TeamMeta.WEREWOLF,
            new Werewolf(),
            1,
            0,
            "peopleWerewolf",
            null,
            true,
            false,
            "wc",
            "/wc <text> - 人狼チャット"),
    SEER("預言者",
            TeamMeta.HUMAN,
            new Seer(),
            0,
            1,
            "peopleSeer",
            "abilitiesSeer",
            false,
            true,
            "ft",
            "/ft <player> - 対象を占う"),
    MEDIUM("霊媒師",
            TeamMeta.HUMAN,
            new Medium(),
            0,
            1,
            "peopleMedium",
            "abilitiesMedium",
            false,
            true,
            "sp",
            "/sp <player> - 対象を霊視する"),
    MADMAN("狂人",
            TeamMeta.HUMAN,
            new Madman(),
            0,
            0,
            "peopleMadman",
            null,
            false,
            false,
            null,
            "特殊能力なし");

    public String jName;
    public TeamMeta team;
    private RoleBuilder builder;
    public int numberOfPeopleMin;
    public int numberOfAbilitiesMin;
    public String peopleConfigPath;
    public String abilityConfigPath;
    public boolean isInhuman;
    public boolean haveAbility;
    public String abilityCommandName;
    public String abilityDescription;

    RoleMeta(String jName,
             TeamMeta team,
             RoleBuilder builder,
             int numberOfPeopleMin,
             int numberOfAbilitiesMin,
             String peopleConfigPath,
             String abilityConfigPath,
             boolean isInhuman,
             boolean haveAbility,
             String abilityCommandName,
             String abilityDescription) {

        this.jName = jName;
        this.team = team;
        this.builder = builder;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.peopleConfigPath = peopleConfigPath;
        this.abilityConfigPath = abilityConfigPath;
        this.isInhuman = isInhuman;
        this.haveAbility = haveAbility;
        this.abilityCommandName = abilityCommandName;
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

    public String actionBarMessage() {
        String message = "";
        if (team.equals(TeamMeta.HUMAN)) {
            message += DecorationConst.GREEN + jName;
        }

        if (team.equals(TeamMeta.WEREWOLF) || this.equals(RoleMeta.MADMAN)) {
            message += DecorationConst.RED + jName;
        }

        message += DecorationConst.RESET + " : " + this.abilityDescription;

        return message;
    }
}
