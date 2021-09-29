package net.kunmc.lab.werewolf.actor;

import java.util.UUID;

public class Medium extends BaseActor {
    private Medium(UUID uuid) {
        super(uuid, RoleMeta.CITIZEN);
    }

    Medium() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Medium(uuid);
    }
}
