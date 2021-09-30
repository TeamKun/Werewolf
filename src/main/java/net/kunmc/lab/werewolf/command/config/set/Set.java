package net.kunmc.lab.werewolf.command.config.set;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class Set extends Command {
    public Set() {
        super(CommandNameConst.COMMAND_SET);

        children(new Role(), new Others());
    }
}
