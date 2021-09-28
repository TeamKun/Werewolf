package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;

import java.util.UUID;

public class Citizen implements Actor,RoleBuilder {
    final Roles role = Roles.CITIZEN;
    private UUID uuid;
    private boolean isDead = false;

    private Citizen(UUID uuid) {
        this.uuid = uuid;
    }
    Citizen() {}

    public Actor instance(UUID uuid) {
        return new Citizen(uuid);
    }

    public UUID uuid() {
        return this.uuid;
    }

    public String roleName() {
        return role.jName;
    }

    public boolean isInhuman() {
        return role.isInhuman;
    }

    public void death() {
        this.isDead = true;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public Teams team() {
        return role.team;
    }
}