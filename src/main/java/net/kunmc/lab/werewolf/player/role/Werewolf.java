package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Werewolf implements WWPlayer, RoleBuilder {
    final Roles role = Roles.WEREWOLF;
    private UUID uuid;

    private Werewolf(UUID uuid) {
        this.uuid = uuid;
    }

    Werewolf() {}

    public WWPlayer instance(UUID uuid) {
        return new Werewolf(uuid);
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
