package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;

import java.util.UUID;

public interface Actor {
    String roleName();

    String actorName();

    UUID uuid();

    boolean isInhuman();

    boolean isWerewolf();

    void death();

    boolean isDead();

    Teams team();

    void showActionBar();

    CommandResult useAbilities(RoleMeta role, Object arg);
}
