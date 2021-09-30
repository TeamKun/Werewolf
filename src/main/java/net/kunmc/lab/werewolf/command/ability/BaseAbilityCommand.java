package net.kunmc.lab.werewolf.command.ability;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.game.GameManager;

public abstract class BaseAbilityCommand extends Command {
    private RoleMeta roleMeta;
    protected BaseAbilityCommand(RoleMeta roleMeta) {
        super(roleMeta.abilityCommandName);
        this.roleMeta = roleMeta;
    }

    @Override
    public void execute(CommandContext ctx) {
        try {
            CommandResult result = GameManager.useAbilities(ctx.getSender(), this.roleMeta, ctx.getTypedArgs().get(0));

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
