package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.command.CommandResult;
import net.kunmc.lab.werewolf.player.Actor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Citizen implements Actor,RoleBuilder {
    final Roles role = Roles.CITIZEN;
    private UUID uuid;
    private boolean isDead = false;

    private Citizen(UUID uuid) {
        this.uuid = uuid;
    }
    Citizen() {}

    public Actor instance(UUID uuid) {
        return new Citizen(uuid);
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