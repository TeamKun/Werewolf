package net.kunmc.lab.werewolf.player;

import net.kunmc.lab.werewolf.player.role.Teams;

import java.util.UUID;

public interface Actor {
    public String roleName();
    public UUID uuid();
    public boolean isInhuman();
    public void death();
    public boolean isDead();
    public Teams team();
    public void showActionBar();
}
