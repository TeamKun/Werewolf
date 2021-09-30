package net.kunmc.lab.werewolf.command.ability;

import net.kunmc.lab.werewolf.actor.RoleMeta;

public class Fortune extends BaseAbilityCommand {
    public Fortune() {
        super(RoleMeta.SEER);

        usage(usageBuilder -> {
            usageBuilder.entityArgument("target");
        });
    }
}
