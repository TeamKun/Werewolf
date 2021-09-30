package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.meta.RoleMeta;

import java.util.UUID;

public class Citizen extends BaseActor {
    private Citizen(UUID uuid) {
        super(uuid, RoleMeta.CITIZEN);
    }

    public Citizen() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Citizen(uuid);
    }
}