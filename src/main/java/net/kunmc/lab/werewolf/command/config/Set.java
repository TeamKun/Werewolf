package net.kunmc.lab.werewolf.command.config;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.command.config.numberofabilities.NumberOfAbilities;
import net.kunmc.lab.werewolf.command.config.numberofpeople.NumberOfPeople;

public class Set extends Command {
    public Set() {
        super(CommandNameConst.COMMAND_SET);

        children(new NumberOfAbilities(), new NumberOfPeople());
    }
}
