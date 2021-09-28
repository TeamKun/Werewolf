package net.kunmc.lab.werewolf.command.config.numberofabilities;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigPathConst;
import net.kunmc.lab.werewolf.player.role.Roles;
import org.jetbrains.annotations.NotNull;

public class ASeer extends Command {
    public ASeer() {
        super(CommandNameConst.COMMAND_SEER);

        usage(usageBuilder -> {
            usageBuilder.integerArgument("回数");
        });
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        try {
            int value = Roles.SEER.numberOfAbilities((int)ctx.getTypedArgs().get(0));
            ConfigManager.setConfig(ConfigPathConst.ABILITY_SEER, value);
            ctx.success(Roles.SEER.jName + "の能力使用回数を" + value + "回に設定しました。");
        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
