package net.kunmc.lab.werewolf.command.config.numberofpeople;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.actor.RoleMeta;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigPathConst;
import org.jetbrains.annotations.NotNull;

class Werewolf extends Command {
    public Werewolf() {
        super(CommandNameConst.COMMAND_WEREWOLF);

        usage(usageBuilder -> {
            usageBuilder.integerArgument("人数");
        });
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        try {
            int value = RoleMeta.WEREWOLF.numberOfPeople((int) ctx.getTypedArgs().get(0));
            ConfigManager.setConfig(ConfigPathConst.PEOPLE_WEREWOLF, value);
            ctx.success(RoleMeta.WEREWOLF.jName + "の人数を" + value + "人に設定しました。");
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
