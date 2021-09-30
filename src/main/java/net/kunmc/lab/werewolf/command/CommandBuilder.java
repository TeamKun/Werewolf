package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.meta.AbilityMeta;

public class CommandBuilder {
    public static void buildMainCommand(Werewolf plugin) {
        FlyLib.create(plugin, builder -> {
            // メインコマンド
            builder.command(new Main());

            // アビリティコマンド
            for (AbilityMeta abilityMeta : AbilityMeta.values()) {
                builder.command(new AbilityCommand(abilityMeta));
            }
        });
    }
}
