package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.meta.RoleMeta;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Function;

public class GeneralActor extends BaseActor {
    public GeneralActor(UUID uuid, RoleMeta role) {
        super(uuid, role);
    }

    @Override
    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        if (player != null) {
            player.sendActionBar(Component.text(this.roleMeta.actionBarMessage()));
        }
    }

    @Override
    public CommandResult useAbility(AbilityArgument arg, Function<AbilityArgument, CommandResult> action) {
        if (!this.roleMeta.equals(arg.roleMeta())) {
            return new CommandResult(false, "あなたはこの能力を使用できません");
        }

        return action.apply(arg);
    }
}
