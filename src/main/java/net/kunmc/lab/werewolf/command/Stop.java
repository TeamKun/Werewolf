package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.logic.GameLogic;
import org.jetbrains.annotations.NotNull;

class Stop extends Command {
    public Stop() {
        super(CommandNameConst.COMMAND_STOP);
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        if (!GameLogic.stop()) {
            ctx.fail("ゲーム実行中ではありません");
            return;
        }

        ctx.success("ゲームを強制終了しました。");
    }
}
