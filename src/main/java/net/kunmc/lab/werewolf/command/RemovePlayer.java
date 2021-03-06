package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.logic.GameLogic;
import org.bukkit.entity.Player;

import java.util.List;

class RemovePlayer extends Command {
    public RemovePlayer() {
        super(CommandNameConst.COMMAND_REMOVE_PLAYER);

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
                    if (GameLogic.removePlayer((Player) arg)) {
                        count++;
                    }
                }
            }
            ctx.success(count + "人のプレイヤーを削除しました。");
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
