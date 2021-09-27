package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;
import net.kunmc.lab.werewolf.player.role.Roles;

import java.util.UUID;

public class Seer implements WWPlayer {
    final Roles role = Roles.SEER;
    private UUID uuid;

    public Seer(UUID uuid) {
        this.uuid = uuid;
    }

    public String roleName() {
        return role.jName();
    }

    public boolean isInhuman() {
        return role.isInhuman();
    }
}
