package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.game.GameManager;
import net.kunmc.lab.werewolf.actor.RoleMeta;

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
