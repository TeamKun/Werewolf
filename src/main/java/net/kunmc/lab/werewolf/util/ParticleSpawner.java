package net.kunmc.lab.werewolf.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleSpawner extends BukkitRunnable {
    private int startTick;
    private Particle particle;
    private int duration;
    private Player target;
    private Location location;
    private int count;

    public ParticleSpawner(Particle particle, int duration, Player target, int count) {
        this.particle = particle;
        this.duration = duration;
        this.target = target;
        this.count = count;
        this.startTick = Bukkit.getCurrentTick();
    }

    @Override
    public void run() {
        int elapsedTick = Bukkit.getCurrentTick() - this.startTick;
        if (elapsedTick > duration) {
            this.cancel();
        }
        this.target.spawnParticle(this.particle, this.target.getLocation(), this.count);
    }
}
