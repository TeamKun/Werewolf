package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.game.GameManager;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Seer extends BaseSpecialActor {
    private Seer(UUID uuid) {
        super(uuid, RoleMeta.SEER);
    }

    Seer() {
        super();
    }

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
        Actor targetActor = GameManager.getActor(target.getUniqueId());

        if (targetActor == null) {
            return new CommandResult(false, "対象はゲームに参加していません");
        }

        if (this.uuid.equals(target.getUniqueId())) {
            return new CommandResult(false, "自分を占うことはできません");
        }

        if (!this.skillPoint.canUse()) {
            return new CommandResult(false, "能力の使用回数が残っていません");
        }

        int currentPoint = this.skillPoint.useSkill();

        String resultMessage = "";

        // 能力を使用する
        if (targetActor.isDead()) {
            resultMessage += targetActor.actorName() + "はすでに死亡しています\n";
        } else if (targetActor.isInhuman()) {
            resultMessage += targetActor.actorName() + "は人間ではありません\n";
        } else {
            resultMessage += targetActor.actorName() + "は人間です\n";
        }

        resultMessage += "残り" + currentPoint + "回";

        return new CommandResult(true, resultMessage);
    }
}
