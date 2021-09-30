package net.kunmc.lab.werewolf.command.config.numberofpeople;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigPathConst;
import org.jetbrains.annotations.NotNull;

class Madman extends Command {
    public Madman() {
        super(CommandNameConst.COMMAND_MADMAN);

        usage(usageBuilder -> {
            usageBuilder.integerArgument("人数");
        });
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        try {
            int value = RoleMeta.MADMAN.numberOfPeople((int) ctx.getTypedArgs().get(0));
            ConfigManager.setConfig(ConfigPathConst.PEOPLE_MADMAN, value);
            ctx.success(RoleMeta.MADMAN.jName + "の人数を" + value + "人に設定しました。");
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
