package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.command.CommandResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class BaseActor implements Actor, RoleBuilder {
    protected Roles role;
    protected UUID uuid;
    protected boolean isDead;

    protected BaseActor() {}
    protected BaseActor(UUID uuid, Roles role) {
        this.role = role;
        this.uuid = uuid;
    }

    public UUID uuid() {
        return this.uuid;
    }

    public String roleName() {
        return role.jName;
    }

    public boolean isInhuman() {
        return role.isInhuman;
    }

    public void death() {
        this.isDead = true;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public Teams team() {
        return role.team;
    }

    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        player.sendActionBar(this.role.actionBarMessage());
    }

    public CommandResult useAbilities(Roles role, Object arg) {
        return new CommandResult(false, "あなたはこの能力を使用できません");
    }
}
