package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.meta.RoleMeta;
import net.kunmc.lab.werewolf.meta.TeamMeta;

import java.util.UUID;

public interface Actor {
    String roleName();

    String actorName();

    UUID uuid();

    boolean isInhuman();

    boolean isWerewolf();

    void death();

    boolean isDead();

    TeamMeta team();

    void showActionBar();

    CommandResult useAbilities(RoleMeta role, Object arg);
}
