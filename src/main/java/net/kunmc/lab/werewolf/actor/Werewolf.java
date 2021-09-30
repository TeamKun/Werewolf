package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.game.GameManager;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.UUID;

public class Werewolf extends BaseActor {
    private Werewolf(UUID uuid) {
        super(uuid, RoleMeta.WEREWOLF);
    }

    Werewolf() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Werewolf(uuid);
    }

    @Override
    public CommandResult useAbilities(RoleMeta role, Object arg) {

        if (!this.roleMeta.equals(role)) {
            return new CommandResult(false, "あなたはこの能力を使用できません");
        }

        // 人狼チャット
        List<Actor> werewolfList = GameManager.getWerewolfList();

        String message = DecorationConst.GRAY + "<人狼チャット : " + this.actorName() + ">" + arg.toString();

        werewolfList.forEach(actor -> {
            Bukkit.getPlayer(actor.uuid()).sendMessage(Component.text(message));
        });

        return new CommandResult(true, null);
    }
}
