package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.BaseActor;
import net.kunmc.lab.werewolf.player.Roles;

import java.util.UUID;

public class Werewolf extends BaseActor {
    private Werewolf(UUID uuid) {
        super(uuid, Roles.CITIZEN);
    }

    public Werewolf() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Werewolf(uuid);
    }
}
