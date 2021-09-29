package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

public class Madman extends BaseActor {
    private Madman(UUID uuid) {
        super(uuid, RoleMeta.MADMAN);
    }

    Madman() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Madman(uuid);
    }
}
