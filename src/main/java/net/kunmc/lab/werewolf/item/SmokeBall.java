package net.kunmc.lab.werewolf.item;

import net.kunmc.lab.werewolf.Werewolf;
import net.kunmc.lab.werewolf.logic.GameLogic;
import net.kunmc.lab.werewolf.util.ParticleSpawner;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SmokeBall implements SpecialItem {
    private List<Player> targetList;

    public void use(Player user) {
        int duration = 200;

        this.targetList = GameLogic.actorList.getPlayerList(user.getUniqueId());
        targetList.forEach(target -> {
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 0, true, true));
            new ParticleSpawner(Particle.SMOKE_NORMAL, duration, target, 100).runTaskTimerAsynchronously(Werewolf.plugin,0, 5);
        });
    }
}