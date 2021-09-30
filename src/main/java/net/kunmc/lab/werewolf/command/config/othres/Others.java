package net.kunmc.lab.werewolf.command.config.othres;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.OthersConfig;

public class Others extends Command {
    public Others() {
        super(CommandNameConst.COMMAND_OTHERS);

        try {
            for (OthersConfig othersConfig : OthersConfig.values()) {
                children(new ConfigItem(othersConfig));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
