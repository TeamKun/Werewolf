package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.AbilityArgument;
import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.meta.RoleMeta;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Function;

public class SpecialActor extends BaseActor {
    private SkillPoint skillPoint;

    public SpecialActor(UUID uuid, RoleMeta roleMeta) {
        super(uuid, roleMeta);
        this.skillPoint = new SkillPoint(ConfigManager.roleConfig(roleMeta).ability());
    }

    @Override
    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        String message = this.roleMeta.actionBarMessage() + " - 残り" + this.skillPoint.current() + "回";
        player.sendActionBar(Component.text(message));
    }

    public CommandResult useAbility(AbilityArgument arg, Function<AbilityArgument, CommandResult> action) {
        if (!this.roleMeta.equals(arg.roleMeta())) {
            return new CommandResult(false, "あなたはこの能力を使用できません");
        }

        if (!this.skillPoint.canUse()) {
            return new CommandResult(false, "能力の使用回数が残っていません");
        }

        CommandResult result = action.apply(arg);
        if(result.isSuccess()) {
            int currentPoint = this.skillPoint.useSkill();
            this.player().sendMessage("能力使用回数: あと" + currentPoint + "回");
        }

        return result;
    }
}
