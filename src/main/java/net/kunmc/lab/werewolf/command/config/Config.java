package net.kunmc.lab.werewolf.command.config;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class Config extends Command {
    public Config() {
        super(CommandNameConst.COMMAND_CONFIG);

        children(new Set(), new Show());
    }
}
