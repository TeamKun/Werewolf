package net.kunmc.lab.werewolf.command.ability;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import dev.kotx.flylib.command.arguments.TextArgument;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.game.GameManager;

public class WolfChat extends Command {
    public WolfChat() {
        super(CommandNameConst.COMMAND_WOLF_CHAT);

        usage(usageBuilder -> {
            usageBuilder.textArgument("message", TextArgument.Type.PHRASE);
        });
    }

    @Override
    public void execute(CommandContext ctx) {
        try {
            CommandResult result = GameManager.useAbilities(ctx.getSender(), RoleMeta.WEREWOLF, ctx.getTypedArgs().get(0));

            if (!result.isSuccess()) {
                ctx.fail(result.message());
            }
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
