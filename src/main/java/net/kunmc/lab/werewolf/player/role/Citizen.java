package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.BaseActor;
import net.kunmc.lab.werewolf.player.Roles;

import java.util.UUID;

public class Citizen extends BaseActor {
    private Citizen(UUID uuid) {
        super(uuid, Roles.CITIZEN);
    }

    public Citizen() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Citizen(uuid);
    }
}