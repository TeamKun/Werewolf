package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Werewolf implements WWPlayer {
    final Roles role = Roles.WEREWOLF;
    private UUID uuid;

    public Werewolf(UUID uuid) {
        this.uuid = uuid;
    }

    public String roleName() {
        return role.jName();
    }

    public boolean isInhuman() {
        return role.isInhuman();
    }
}
