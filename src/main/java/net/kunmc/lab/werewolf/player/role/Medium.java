package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Medium implements Actor, RoleBuilder {
    final Roles role = Roles.MEDIUM;
    private UUID uuid;
    private boolean isDead = false;

    private Medium(UUID uuid) {
        this.uuid = uuid;
    }

    Medium() {}

    public Actor instance(UUID uuid) {
        return new Medium(uuid);
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
}
