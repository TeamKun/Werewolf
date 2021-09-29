package net.kunmc.lab.werewolf.command.config.numberofpeople;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class NumberOfPeople extends Command {
    public NumberOfPeople() {
        super(CommandNameConst.COMMAND_NUMBER_OF_PEOPLE);

        children(new Medium(), new Seer(), new Madman(), new Werewolf());
    }
}
