package net.kunmc.lab.werewolf.player;

import java.util.UUID;

public interface WWPlayer {
    public String roleName();
    public UUID uuid();
    public boolean isInhuman();
}
