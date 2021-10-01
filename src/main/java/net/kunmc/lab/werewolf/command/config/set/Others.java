package net.kunmc.lab.werewolf.command.config.set;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.config.ConfigMeta;

class Others extends Command {
    public Others() {
        super(CommandNameConst.COMMAND_OTHERS);

        try {
            for (ConfigMeta othersConfig : ConfigMeta.values()) {
                children(new ConfigItem(othersConfig));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
