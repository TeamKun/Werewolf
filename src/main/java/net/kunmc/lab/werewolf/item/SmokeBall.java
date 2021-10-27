package net.kunmc.lab.werewolf.item;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.util.ParticleSpawner;
import net.kunmc.lab.werewolf.util.Second;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SmokeBall implements SpecialItem {
    private List<Player> targetList;

    public void use(Player user) {
        Second second = new Second(10);

        this.targetList = GameLogic.actorList.getPlayerList(user.getUniqueId());
        targetList.forEach(target -> {
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, second.tick(), 0, true, true));
            new ParticleSpawner(Particle.SMOKE_NORMAL, second, target, 100).runTaskTimerAsynchronously(Werewolf.plugin,0, 5);
        });
    }
}