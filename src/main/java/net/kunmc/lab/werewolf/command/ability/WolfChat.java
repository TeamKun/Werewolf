package net.kunmc.lab.werewolf.command.ability;

import dev.kotx.flylib.command.arguments.TextArgument;
import net.kunmc.lab.werewolf.actor.RoleMeta;

public class WolfChat extends BaseAbilityCommand {
    public WolfChat() {
        super(RoleMeta.WEREWOLF);

        usage(usageBuilder -> {
            usageBuilder.textArgument("message", TextArgument.Type.PHRASE);
        });
    }
}