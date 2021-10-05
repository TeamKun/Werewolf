package net.kunmc.lab.werewolf.player;

import dev.kotx.flylib.command.UsageBuilder;
import dev.kotx.flylib.command.arguments.TextArgument;
import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public enum AbilityMeta {
    WOLF_CHAT(RoleMeta.WEREWOLF,
            "wc",
            usageBuilder -> {
                usageBuilder.textArgument("message", TextArgument.Type.PHRASE);
            },
            String.class,
            (user, args)-> {
                // 人狼チャット
                List<Actor> werewolfList = GameLogic.getWerewolfList();

                String message = DecorationConst.ITALIC+ "<人狼チャット : " + user.player().getName() + "> " + args[0].value();

                werewolfList.forEach(actor -> {
                    Bukkit.getPlayer(actor.uuid()).sendMessage(message);
                });

                return new CommandResult(true, null);
            }),
    FORTUNE(RoleMeta.SEER,
            "ft",
            usageBuilder -> {
                usageBuilder.textArgument("target", suggestionBuilder -> {
                    GameLogic.getPlayerList().forEach(player -> {
                        suggestionBuilder.suggest(player.getName());
                    });
                });
            },
            Player.class,
            (user, args) -> {
                Player target = (Player) args[0].value();
                if (target == null) {
                    return new CommandResult(false, "存在しないプレイヤーです");
                }

                Actor targetActor = GameLogic.getActor(target.getUniqueId());

                if (targetActor == null) {
                    return new CommandResult(false, "対象はゲームに参加していません");
                }

                String resultMessage = "";

                // 能力を使用する
                if (targetActor.isDead()) {
                    resultMessage += targetActor.actorName() + "はすでに死亡しています\n";
                } else if (targetActor.isInhuman()) {
                    resultMessage += targetActor.actorName() + "は人間ではありません\n";
                } else {
                    resultMessage += targetActor.actorName() + "は人間です\n";
                }

                return new CommandResult(true, resultMessage);
            }),
    SPIRITUAL(RoleMeta.MEDIUM,
            "sp",
            usageBuilder -> {
                usageBuilder.textArgument("target", suggestionBuilder -> {
                    GameLogic.getPlayerList().forEach(player -> {
                        suggestionBuilder.suggest(player.getName());
                    });
                });
            },
            Player.class,
            (user, args) -> {
                Player target = (Player) args[0].value();
                if (target == null) {
                    return new CommandResult(false, "存在しないプレイヤーです");
                }

                Actor targetActor = GameLogic.getActor(target.getUniqueId());

                if (targetActor == null) {
                    return new CommandResult(false, "対象はゲームに参加していません");
                }

                String resultMessage = "";

                // 能力を使用する
                if (!targetActor.isDead()) {
                    resultMessage += targetActor.actorName() + "は生存しています\n";
                } else if (targetActor.isInhuman()) {
                    resultMessage += targetActor.actorName() + "は人間ではありません\n";
                } else {
                    resultMessage += targetActor.actorName() + "は人間です\n";
                }

                return new CommandResult(true, resultMessage);
            });

    public RoleMeta roleMeta;
    public String commandName;
    private Consumer<UsageBuilder> usage;
    public Class argType;
    public AbilityAction action;

    AbilityMeta(RoleMeta roleMeta,
                String commandName,
                Consumer<UsageBuilder> usage,
                Class argType,
                AbilityAction action) {
        this.roleMeta = roleMeta;
        this.commandName = commandName;
        this.usage = usage;
        this.argType = argType;
        this.action = action;
    }

    public void appendUsage(UsageBuilder usageBuilder) {
        usage.accept(usageBuilder);
    }

    public AbilityArgument getArgument(Object arg) {

        // String
        if (this.argType.equals(String.class)) {
            return new AbilityArgument<>(this, (String) arg);
        }

        // Player
        if (this.argType.equals(Player.class)) {
            Player player = Bukkit.getPlayer((String) arg);
            return new AbilityArgument<>(this, player);
        }

        return null;
    }
}
