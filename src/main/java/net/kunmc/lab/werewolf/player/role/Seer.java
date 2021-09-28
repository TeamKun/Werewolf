package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Seer implements Actor, RoleBuilder {
    final Roles role = Roles.SEER;
    private UUID uuid;
    private boolean isDead = false;

    private Seer(UUID uuid) {
        this.uuid = uuid;
    }

    Seer() {}

    public Actor instance(UUID uuid) {
        return new Seer(uuid);
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
