package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;

import java.util.UUID;

public interface RoleBuilder {
    public Actor instance(UUID uuid);
}
