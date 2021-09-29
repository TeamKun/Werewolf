package net.kunmc.lab.werewolf.config;

import net.kunmc.lab.werewolf.actor.RoleMeta;

 public class RoleConfig {
    private RoleMeta role;
    private int people;
    private int ability;

    RoleConfig(RoleMeta role, int people, int ability) {
        this.role = role;
        setPeople(people);
        setAbility(ability);
    }

    void setPeople(int value) {
        if (this.role.numberOfPeopleMin > value) {
            value = this.role.numberOfPeopleMin;
        }
        this.people = value;
    }

    void setAbility(int value) {
        if (this.role.numberOfAbilitiesMin > value) {
            value = this.role.numberOfPeopleMin;
        }
        this.ability = value;
    }

    public RoleMeta role() {
        return this.role;
    }

    public int people() {
        return this.people;
    }

    public int ability() {
        return this.ability;
    }
}
