package net.kunmc.lab.werewolf.command.config.show;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;

public class Show extends Command {
    public Show() {
        super(CommandNameConst.COMMAND_SHOW);

        children(new Role(), new Others());
    }
}
