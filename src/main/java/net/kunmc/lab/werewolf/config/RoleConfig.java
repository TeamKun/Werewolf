package net.kunmc.lab.werewolf.config;

import net.kunmc.lab.werewolf.player.role.Roles;

public class RoleConfig {
    private Roles role;
    private int people;
    private int ability;

    RoleConfig(Roles role, int people, int ability) {
        this.role = role;
        setPeople(people);
        setAbility(ability);
    }

    void setPeople(int value) {
        if (this.role.numberOfPeopleMin() > value) {
            value = this.role.numberOfPeopleMin();
        }
        this.people = value;
    }

    void setAbility(int value) {
        if (this.role.numberOfAbilitiesMin() > value) {
            value = this.role.numberOfPeopleMin();
        }
        this.ability = value;
    }

    int people() {
        return this.people;
    }

    int ability() {
        return this.ability;
    }
}
