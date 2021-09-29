package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

public class Werewolf extends BaseActor {
    private Werewolf(UUID uuid) {
        super(uuid, RoleMeta.CITIZEN);
    }

    Werewolf() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Werewolf(uuid);
    }
}
