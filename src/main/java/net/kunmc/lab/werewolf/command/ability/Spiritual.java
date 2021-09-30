package net.kunmc.lab.werewolf.command.ability;

import net.kunmc.lab.werewolf.actor.RoleMeta;

public class Spiritual extends BaseAbilityCommand {
    public Spiritual() {
        super(RoleMeta.MEDIUM);

        usage(usageBuilder -> {
            usageBuilder.entityArgument("target");
        });
    }
}
