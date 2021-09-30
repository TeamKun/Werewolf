package net.kunmc.lab.werewolf.meta;

import dev.kotx.flylib.command.UsageBuilder;
import dev.kotx.flylib.command.arguments.TextArgument;
import net.kunmc.lab.werewolf.game.GameManager;

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
                usageBuilder.textArgument("target", suggestionBuilder -> {
                    GameManager.getPlayerList().forEach(player -> {
                        suggestionBuilder.suggest(player.getName());
                    });
                });
            }),
    SPIRITUAL(RoleMeta.MEDIUM,
            "sp",
            usageBuilder -> {
                usageBuilder.textArgument("target", suggestionBuilder -> {
                    GameManager.getPlayerList().forEach(player -> {
                        suggestionBuilder.suggest(player.getName());
                    });
                });
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
