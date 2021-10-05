package net.kunmc.lab.werewolf.logic;

import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class UserInterface extends BukkitRunnable {

    @Override
    public void run() {
        if (!GameLogic.isRunning()) {
            return;
        }

        if (!(Boolean) ConfigManager.getOthersConfig(ConfigMeta.IS_SHOW_ACTIONBAR)) {
            return;
        }

        GameLogic.actorList.showActionBar();
    }
}
