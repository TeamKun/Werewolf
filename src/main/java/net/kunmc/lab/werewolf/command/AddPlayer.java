package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.logic.GameLogic;
import org.bukkit.entity.Player;

import java.util.List;

class AddPlayer extends Command {
    public AddPlayer() {
        super(CommandNameConst.COMMAND_ADD_PLAYER);

        usage(usageBuilder -> {
            usageBuilder.entityArgument("player");
        });
    }

    @Override
    public void execute(CommandContext ctx) {
        try {
            int count = 0;
            for (Object arg : ((List) ctx.getTypedArgs().get(0))) {
                if (arg instanceof Player) {
                    if (GameLogic.addPlayer((Player) arg)) {
                        count++;
                    }
                }
            }
            ctx.success(count + "人のプレイヤーを追加しました。");
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
