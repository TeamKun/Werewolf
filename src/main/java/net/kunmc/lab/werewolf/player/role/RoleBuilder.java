package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.WWPlayer;

import java.util.UUID;

public interface RoleBuilder {
    public WWPlayer instance(UUID uuid);
}
