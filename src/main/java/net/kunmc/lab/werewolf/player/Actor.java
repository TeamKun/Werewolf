package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import org.bukkit.entity.Player;

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

    Player player();

    CommandResult useAbility(AbilityArgument arg, AbilityAction action);
}
