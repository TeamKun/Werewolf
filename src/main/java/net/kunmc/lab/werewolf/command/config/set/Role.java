package net.kunmc.lab.werewolf.command.config.set;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.werewolf.command.CommandNameConst;
import net.kunmc.lab.werewolf.player.RoleMeta;

class Role extends Command {
    public Role() {
        super(CommandNameConst.COMMAND_ROLE);

        for (RoleMeta roleMeta : RoleMeta.values()) {
            if (!roleMeta.equals(RoleMeta.CITIZEN)) {
                children(new RoleCommand(roleMeta));
            }
        }
    }
}
