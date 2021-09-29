package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

interface RoleBuilder {
    Actor instance(UUID uuid);
}
