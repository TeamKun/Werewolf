package net.kunmc.lab.werewolf.command.ability;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.game.GameManager;

public class Fortune extends Command {
    public Fortune() {
        super(CommandNameConst.COMMAND_FORTUNE);

        usage(usageBuilder -> {
            usageBuilder.entityArgument("target");
        });
    }

    @Override
    public void execute(CommandContext ctx) {
        try {
            CommandResult result = GameManager.useAbilities(ctx.getSender(), RoleMeta.SEER, ctx.getTypedArgs().get(0));

            if (result.isSuccess()) {
                ctx.success(result.message());
            } else {
                ctx.fail(result.message());
            }

        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}