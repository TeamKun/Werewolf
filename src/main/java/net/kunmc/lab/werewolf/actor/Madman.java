package net.kunmc.lab.werewolf.actor;

import net.kunmc.lab.werewolf.meta.RoleMeta;

import java.util.UUID;

public class Madman extends BaseActor {
    private Madman(UUID uuid) {
        super(uuid, RoleMeta.MADMAN);
    }

    public Madman() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Madman(uuid);
    }
}
