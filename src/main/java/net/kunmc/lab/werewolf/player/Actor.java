package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.meta.TeamMeta;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Function;

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

    CommandResult useAbility(AbilityArgument arg, Function<AbilityArgument, CommandResult> action);
}
