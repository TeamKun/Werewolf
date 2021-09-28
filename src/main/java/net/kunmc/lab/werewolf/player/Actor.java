package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.command.CommandResult;

import java.util.UUID;

public interface Actor {
    String roleName();
    UUID uuid();
    boolean isInhuman();
    void death();
    boolean isDead();
    Teams team();
    void showActionBar();
    CommandResult useAbilities(Roles role, Object arg);
}
