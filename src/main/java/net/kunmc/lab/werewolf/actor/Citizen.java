package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

public class Citizen extends BaseActor {
    private Citizen(UUID uuid) {
        super(uuid, RoleMeta.CITIZEN);
    }

    Citizen() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Citizen(uuid);
    }
}