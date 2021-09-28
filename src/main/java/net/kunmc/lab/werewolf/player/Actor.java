package net.kunmc.lab.werewolf.player;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.player.role.Roles;
import net.kunmc.lab.werewolf.player.role.Teams;

import java.util.UUID;

public interface Actor {
    public String roleName();
    public UUID uuid();
    public boolean isInhuman();
    public void death();
    public boolean isDead();
    public Teams team();
    public void showActionBar();
    public CommandResult useAbilities(Roles role, Object arg);
}
