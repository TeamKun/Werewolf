package net.kunmc.lab.werewolf.game;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.meta.ConfigMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class UserInterface extends BukkitRunnable {

    @Override
    public void run() {
        if (!GameManager.isIsRunning()) {
            return;
        }

        if (!(Boolean) ConfigManager.getOthersConfig(ConfigMeta.IS_SHOW_ACTIONBAR)) {
            return;
        }

        GameManager.actorList.showActionBar();
    }
}
