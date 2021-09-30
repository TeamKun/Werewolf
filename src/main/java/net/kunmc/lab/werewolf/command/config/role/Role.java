package net.kunmc.lab.werewolf.command.config.role;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class Role extends Command {
    public Role() {
        super(CommandNameConst.COMMAND_ROLE);

        children(new Medium(), new Seer(), new Werewolf(), new Madman());
    }
}
