package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import org.bukkit.entity.Player;

import java.util.List;

public class Spiritual extends Command {
    public Spiritual() {
        super(CommandNameConst.COMMAND_FORTUNE);

        usage(usageBuilder -> {
            usageBuilder.entityArgument("target");
        });
    }

    @Override
    public void execute(CommandContext ctx) {
        try {

            List<Object> targetList = (List) ctx.getTypedArgs().get(0);

            if (targetList.size() != 1) {
                ctx.fail("対象とできるのは１人のみです。");
                return;
            }

            Player target = (Player) targetList.get(0);

            ctx.success(target.getName() + "を霊視しました");

        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
