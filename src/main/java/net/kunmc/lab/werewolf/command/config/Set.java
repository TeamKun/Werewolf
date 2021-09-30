package net.kunmc.lab.werewolf.command.config;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.command.config.role.Role;

class Set extends Command {
    public Set() {
        super(CommandNameConst.COMMAND_SET);

        children(new Role());
    }
}
