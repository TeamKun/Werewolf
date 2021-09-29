package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.config.Config;

public class Main extends Command {
    public Main() {
        super(CommandNameConst.COMMAND_MAIN);
        children(new Start(), new Stop(), new AddPlayer(), new RemovePlayer(), new Config());
    }
}