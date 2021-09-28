package net.kunmc.lab.werewolf.game;

import org.bukkit.scheduler.BukkitRunnable;

public class UserInterface extends BukkitRunnable {

    @Override
    public void run() {
        if (!GameManager.isIsRunning()) {
            return;
        }
        GameManager.actorList.showActionBar();
    }
}
