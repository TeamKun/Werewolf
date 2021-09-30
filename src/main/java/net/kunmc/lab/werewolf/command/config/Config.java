package net.kunmc.lab.werewolf.command.config;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.command.config.set.Set;
import net.kunmc.lab.werewolf.command.config.show.Show;

public class Config extends Command {
    public Config() {
        super(CommandNameConst.COMMAND_CONFIG);

        children(new Set(), new Show(), new Reload());
    }
}
