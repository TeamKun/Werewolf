package net.kunmc.lab.werewolf.item;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.util.ParticleSpawner;
import net.kunmc.lab.werewolf.util.Second;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InvinciblePotion implements SpecialItem  {
    public void use(Player user) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    Second duration = new Second(5);
                    user.setInvulnerable(true);
                    new ParticleSpawner(Particle.TOTEM, duration, user, 10).runTaskTimerAsynchronously(Werewolf.plugin, 0, 5);
                    Thread.sleep(duration.milliSecond());
                    user.setInvulnerable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Werewolf.plugin);
    }
}
