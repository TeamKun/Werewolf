package net.kunmc.lab.werewolf.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.werewolf.logic.GameLogic;
import org.jetbrains.annotations.NotNull;

class Start extends Command {
    public Start() {
        super(CommandNameConst.COMMAND_START);
    }

    @Override
    public void execute(@NotNull CommandContext ctx) {
        if (!GameLogic.start()) {
            ctx.fail("プレイヤーの人数が不足しているためゲームを開始できません。役職を減らしてください。");
            return;
        }

        ctx.success("ゲームを開始します。");
    }
}
