package net.kunmc.lab.werewolf.command.config.show;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;

class Others extends Command {
    public Others() {
        super(CommandNameConst.COMMAND_OTHERS);
    }
    @Override
    public void execute(CommandContext ctx) {
        ctx.success(ConfigManager.showOthersConfig().toString());
    }
}
