package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Citizen implements WWPlayer,RoleBuilder {
    final Roles role = Roles.CITIZEN;
    private UUID uuid;

    private Citizen(UUID uuid) {
        this.uuid = uuid;
    }
    Citizen() {}

    public WWPlayer instance(UUID uuid) {
        return new Citizen(uuid);
    }

    public UUID uuid() {
        return this.uuid;
    }

    public String roleName() {
        return role.jName();
    }

    public boolean isInhuman() {
        return role.isInhuman();
    }
}