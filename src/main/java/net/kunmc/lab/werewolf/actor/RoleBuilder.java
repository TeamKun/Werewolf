package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

public interface RoleBuilder {
    Actor instance(UUID uuid);
}
