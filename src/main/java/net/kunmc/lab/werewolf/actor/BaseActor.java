package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public String actorName() {
        return Bukkit.getPlayer(this.uuid).getName();
    }

    @Override
    public String roleName() {
        return roleMeta.jName;
    }

    @Override
    public boolean isInhuman() {
        return roleMeta.isInhuman;
    }

    @Override
    public boolean isWerewolf() {
        return this.roleMeta.equals(RoleMeta.WEREWOLF);
    }

    @Override
    public void death() {
        this.isDead = true;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public Teams team() {
        return roleMeta.team;
    }

    @Override
    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        player.sendActionBar(Component.text(this.roleMeta.actionBarMessage()));
    }

    @Override
    public CommandResult useAbilities(RoleMeta role, Object arg) {
        return new CommandResult(false, "あなたはこの能力を使用できません");
    }
}
