package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Seer extends BaseActor {
    private Seer(UUID uuid) {
        super(uuid, RoleMeta.CITIZEN);
    }

    Seer() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Seer(uuid);
    }

    @Override
    public CommandResult useAbilities(RoleMeta role, Object arg) {

        if (!this.roleMeta.equals(role)) {
            return new CommandResult(false, "あなたはこの能力を使用できません");
        }

        List<Object> targetList = (List) arg;

        if (targetList.size() != 1) {
            return new CommandResult(false, "対象とできるのは１人のみです。");
        }

        Player target = (Player) targetList.get(0);

        if (this.uuid.equals(target.getUniqueId())) {
            return new CommandResult(false, "自分を占うことはできません");
        }

        return new CommandResult(true, target.getName() + "を占いました");
    }
}
