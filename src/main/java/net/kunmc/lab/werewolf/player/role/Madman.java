package net.kunmc.lab.werewolf.player.role;

import net.kunmc.lab.werewolf.player.Actor;
import net.kunmc.lab.werewolf.player.BaseActor;
import net.kunmc.lab.werewolf.player.Roles;

import java.util.UUID;

public class Madman extends BaseActor {
    private Madman(UUID uuid) {
        super(uuid, Roles.MADMAN);
    }

    public Madman() {
        super();
    }

    @Override
    public Actor instance(UUID uuid) {
        return new Madman(uuid);
    }
}
