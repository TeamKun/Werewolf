package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import dev.kotx.flylib.command.Permission;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.player.AbilityMeta;
import net.kunmc.lab.werewolf.player.RoleMeta;
import org.bukkit.Bukkit;

class AbilityCommand extends Command {
    private AbilityMeta abilityMeta;

    public AbilityCommand(AbilityMeta abilityMeta) {
        super(abilityMeta.commandName);
        this.abilityMeta = abilityMeta;
        this.permission(Permission.getEVERYONE());

        usage(abilityMeta::appendUsage);
    }

    @Override
    public void execute(CommandContext ctx) {
        try {

            AbilityArgument arg = this.abilityMeta.getArgument(ctx.getTypedArgs().get(0));

            CommandResult result = GameLogic.useAbilities(ctx.getSender(), arg, this.abilityMeta.action);

            if (result.isSuccess() && result.message() != null) {
                ctx.success(result.message());
                return;
            }

            if (!result.isSuccess() && result.message() != null) {
                ctx.fail(result.message());
                return;
            }

        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
