package net.kunmc.lab.werewolf.actor;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class BaseSpecialActor extends BaseActor {
    protected SkillPoint skillPoint;

    protected BaseSpecialActor(UUID uuid, RoleMeta roleMeta, SkillPoint skillPoint) {
        super(uuid, roleMeta);
        this.skillPoint = skillPoint;
    }

    protected BaseSpecialActor() {
        super();
    }

    @Override
    public void showActionBar() {
        Player player = Bukkit.getPlayer(this.uuid);
        String message = this.roleMeta.actionBarMessage() + "残り" + this.skillPoint.current() + "回";
        player.sendActionBar(Component.text(message));
    }
}
