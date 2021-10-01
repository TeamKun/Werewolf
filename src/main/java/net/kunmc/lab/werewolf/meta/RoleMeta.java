package net.kunmc.lab.werewolf.meta;

import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.GeneralActor;
import net.kunmc.lab.werewolf.player.SpecialActor;
import net.kunmc.lab.werewolf.util.DecorationConst;

import java.util.UUID;

public enum RoleMeta {
    CITIZEN("市民",
            TeamMeta.HUMAN,
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
    public int numberOfPeopleMin;
    public int numberOfAbilitiesMin;
    public String peopleConfigPath;
    public String abilityConfigPath;
    public boolean isInhuman;
    public boolean isSpecial;
    public String abilityCommandName;
    public String abilityDescription;

    RoleMeta(String jName,
             TeamMeta team,
             int numberOfPeopleMin,
             int numberOfAbilitiesMin,
             String peopleConfigPath,
             String abilityConfigPath,
             boolean isInhuman,
             boolean isSpecial,
             String abilityCommandName,
             String abilityDescription) {

        this.jName = jName;
        this.team = team;
        this.numberOfPeopleMin = numberOfPeopleMin;
        this.numberOfAbilitiesMin = numberOfAbilitiesMin;
        this.peopleConfigPath = peopleConfigPath;
        this.abilityConfigPath = abilityConfigPath;
        this.isInhuman = isInhuman;
        this.isSpecial = isSpecial;
        this.abilityCommandName = abilityCommandName;
        this.abilityDescription = abilityDescription;
    }

    public Actor instance(UUID uuid) {
        if (this.isSpecial) {
            return new SpecialActor(uuid,this);
        }
        return new GeneralActor(uuid, this);
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
