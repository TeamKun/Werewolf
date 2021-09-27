package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Madman implements WWPlayer, RoleBuilder {
    final Roles role = Roles.MADMAN;
    private UUID uuid;

    private Madman(UUID uuid) {
        this.uuid = uuid;
    }

    Madman() {}

    public WWPlayer instance(UUID uuid) {
        return new Madman(uuid);
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
