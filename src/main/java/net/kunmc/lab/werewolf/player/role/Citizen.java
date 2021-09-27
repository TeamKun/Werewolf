package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Citizen implements WWPlayer {
    final Roles role = Roles.CITIZEN;
    private UUID uuid;

    public Citizen(UUID uuid) {
        this.uuid = uuid;
    }

    public String roleName() {
        return role.jName();
    }

    public boolean isInhuman() {
        return role.isInhuman();
    }
}