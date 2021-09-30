package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.meta.RoleMeta;
import net.kunmc.lab.werewolf.meta.TeamMeta;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

abstract class BaseActor implements Actor{
    protected RoleMeta roleMeta;
    protected UUID uuid;
    protected boolean isDead;

    public BaseActor(UUID uuid, RoleMeta role) {
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
    public TeamMeta team() {
        return roleMeta.team;
    }

    @Override
    public Player player() {
        return Bukkit.getPlayer(this.uuid);
    }
}
