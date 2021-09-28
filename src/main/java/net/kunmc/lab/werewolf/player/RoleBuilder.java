package net.kunmc.lab.werewolf.player;

import java.util.UUID;

public interface RoleBuilder {
    Actor instance(UUID uuid);
}
