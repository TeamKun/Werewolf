package net.kunmc.lab.werewolf.command.config.set;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.meta.RoleMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

class RoleCommand extends Command {
    private RoleMeta roleMeta;
    private boolean isSpecialRole;

    public RoleCommand(RoleMeta roleMeta) {
        super(roleMeta.name().toLowerCase(Locale.ROOT));
        this.roleMeta = roleMeta;
        this.isSpecialRole = this.roleMeta.abilityConfigPath != null;

        usage(usageBuilder -> {
            usageBuilder.integerArgument("人数");

            if (isSpecialRole) {
                usageBuilder.integerArgument("能力使用回数");
            }
        });
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        try {
            int people = this.roleMeta.numberOfPeople((int) ctx.getTypedArgs().get(0));
            ConfigManager.setConfig(this.roleMeta.peopleConfigPath, people);
            ctx.success(this.roleMeta.jName + "の人数を" + people + "人に設定しました。");

            if (this.isSpecialRole) {
                int ability = this.roleMeta.numberOfAbilities((int) ctx.getTypedArgs().get(1));
                ConfigManager.setConfig(this.roleMeta.abilityConfigPath, ability);
                ctx.success(this.roleMeta.jName + "の能力使用回数を" + ability + "回に設定しました。");
            }

        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
