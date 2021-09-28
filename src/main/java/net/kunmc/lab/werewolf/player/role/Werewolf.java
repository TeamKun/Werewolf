package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;

import java.util.UUID;

public class Werewolf implements Actor, RoleBuilder {
    final Roles role = Roles.WEREWOLF;
    private UUID uuid;
    private boolean isDead = false;

    private Werewolf(UUID uuid) {
        this.uuid = uuid;
    }

    Werewolf() {}

    public Actor instance(UUID uuid) {
        return new Werewolf(uuid);
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
