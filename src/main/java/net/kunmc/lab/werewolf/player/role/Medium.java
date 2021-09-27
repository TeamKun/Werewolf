package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Medium implements WWPlayer, RoleBuilder {
    final Roles role = Roles.MEDIUM;
    private UUID uuid;

    private Medium(UUID uuid) {
        this.uuid = uuid;
    }

    Medium() {}

    public WWPlayer instance(UUID uuid) {
        return new Medium(uuid);
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
