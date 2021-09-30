package net.kunmc.lab.werewolf.command.config;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;

class Reload extends Command {
    public Reload() {
        super(CommandNameConst.COMMAND_RELOAD);
    }

    @Override
    public void execute(CommandContext ctx) {
        ConfigManager.loadConfig(true);
        ctx.success("コンフィグをリロードしました");
    }
}
