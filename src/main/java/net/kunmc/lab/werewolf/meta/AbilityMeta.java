package net.kunmc.lab.werewolf.meta;

import dev.kotx.flylib.command.UsageBuilder;
import dev.kotx.flylib.command.arguments.TextArgument;
import net.kunmc.lab.werewolf.meta.RoleMeta;

import java.util.function.Consumer;

public enum AbilityMeta {
    WOLF_CHAT(RoleMeta.WEREWOLF,
            "wc",
            usageBuilder -> {
                usageBuilder.textArgument("message", TextArgument.Type.PHRASE);
            }),
    FORTUNE(RoleMeta.SEER,
            "ft",
            usageBuilder -> {
                usageBuilder.entityArgument("target");
            }),
    SPIRITUAL(RoleMeta.MEDIUM,
            "sp",
            usageBuilder -> {
                usageBuilder.entityArgument("target");
            });

    public RoleMeta roleMeta;
    public String commandName;
    private Consumer<UsageBuilder> consumer;

    AbilityMeta(RoleMeta roleMeta, String commandName, Consumer<UsageBuilder> consumer) {
        this.roleMeta = roleMeta;
        this.commandName = commandName;
        this.consumer = consumer;
    }

    public void appendUsage(UsageBuilder usageBuilder) {
        consumer.accept(usageBuilder);
    }
}
