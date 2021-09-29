package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class BaseActor implements Actor, RoleBuilder {
    protected RoleMeta roleMeta;
    protected UUID uuid;
    protected boolean isDead;

    protected BaseActor() {
    }

    protected BaseActor(UUID uuid, RoleMeta role) {
        this.roleMeta = role;
        this.uuid = uuid;
    }

    public UUID uuid() {
        return this.uuid;
    }

    public String roleName() {
        return roleMeta.jName;
    }

    public boolean isInhuman() {
        return roleMeta.isInhuman;
    }

    public void death() {
        this.isDead = true;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public Teams team() {
        return roleMeta.team;
    }

    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        player.sendActionBar(this.roleMeta.actionBarMessage());
    }

    public CommandResult useAbilities(RoleMeta role, Object arg) {
        return new CommandResult(false, "あなたはこの能力を使用できません");
    }
}
