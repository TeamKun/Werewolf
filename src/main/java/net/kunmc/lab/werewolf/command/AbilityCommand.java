package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.game.GameManager;
import net.kunmc.lab.werewolf.meta.AbilityMeta;
import net.kunmc.lab.werewolf.meta.RoleMeta;

class AbilityCommand extends Command {
    private AbilityMeta abilityMeta;
    private RoleMeta roleMeta;
    public AbilityCommand(AbilityMeta abilityMeta) {
        super(abilityMeta.commandName);
        this.abilityMeta = abilityMeta;
        this.roleMeta = abilityMeta.roleMeta;

        usage(abilityMeta::appendUsage);
    }

    @Override
    public void execute(CommandContext ctx) {
        try {

            AbilityArgument arg = this.abilityMeta.getArgument(ctx.getTypedArgs().get(0));

            CommandResult result = GameManager.useAbilities(ctx.getSender(), arg, this.abilityMeta.action);

            if (result.isSuccess() && result.message() != null) {
                ctx.success(result.message());
                return;
            }

            if (!result.isSuccess() && result.message() != null) {
                ctx.fail(result.message());
                return;
            }

        } catch (IndexOutOfBoundsException e) {
            ctx.fail("引数が不正です");
        }
    }
}
