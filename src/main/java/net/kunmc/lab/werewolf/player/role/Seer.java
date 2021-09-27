package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Seer implements WWPlayer, RoleBuilder {
    final Roles role = Roles.SEER;
    private UUID uuid;

    private Seer(UUID uuid) {
        this.uuid = uuid;
    }

    Seer() {}

    public WWPlayer instance(UUID uuid) {
        return new Seer(uuid);
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
