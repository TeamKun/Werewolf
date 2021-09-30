package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.game.GameManager;

public class WolfChat extends Command {
    public WolfChat() {
        super(CommandNameConst.COMMAND_WOLF_CHAT);

        usage(usageBuilder -> {
            usageBuilder.textArgument("chat");
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
