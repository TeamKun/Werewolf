package net.kunmc.lab.werewolf.command.config.show;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class Show extends Command {
    public Show() {
        super(CommandNameConst.COMMAND_SHOW);

        children(new Role(), new Others());
    }
}
