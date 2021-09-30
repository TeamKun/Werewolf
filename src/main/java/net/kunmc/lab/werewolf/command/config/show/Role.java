package net.kunmc.lab.werewolf.command.config.show;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;

public class Role extends Command {
    public Role() {
        super(CommandNameConst.COMMAND_ROLE);
    }
    @Override
    public void execute(CommandContext ctx) {
        ctx.success(ConfigManager.showRoleConfig().toString());
    }
}
