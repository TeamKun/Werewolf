package net.kunmc.lab.werewolf.command.config.numberofpeople;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigPathConst;
import org.jetbrains.annotations.NotNull;

public class NumberOfPeople extends Command {
    public NumberOfPeople() {
        super(CommandNameConst.COMMAND_NUMBER_OF_PEOPLE);

        children(new PMedium(), new PSeer(), new Pmadman() ,new PWerewolf());
    }
}
