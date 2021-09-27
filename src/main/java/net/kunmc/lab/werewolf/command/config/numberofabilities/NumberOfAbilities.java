package net.kunmc.lab.werewolf.command.config.numberofabilities;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;

public class NumberOfAbilities extends Command {
    public NumberOfAbilities() {
        super(CommandNameConst.COMMAND_NUMBER_OF_ABILITIES);

        children(new Amedium(), new ASeer());
    }
}
