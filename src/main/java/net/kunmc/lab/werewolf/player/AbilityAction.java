package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;

import java.util.List;

@FunctionalInterface
public interface AbilityAction {
    CommandResult use(Actor user, AbilityArgument[] args);
}
