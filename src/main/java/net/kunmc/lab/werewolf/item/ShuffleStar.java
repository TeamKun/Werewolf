package net.kunmc.lab.werewolf.item;

import net.kunmc.lab.werewolf.logic.GameLogic;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleStar implements SpecialItem {
    public void use(Player user) {
        List<Player> targetList = GameLogic.actorList.getPlayerList(user.getUniqueId(), false);
        List<Location> locationList = new ArrayList<>();

        // ターゲットの座標を取得してシャッフル
        targetList.forEach(target -> {
            locationList.add(target.getLocation());
        });
        Collections.shuffle(locationList);

        // tp
        for (int i = 0; i < targetList.size(); i++) {
            Player target = targetList.get(i);
            Location location = locationList.get(i);
            target.teleport(location);
        }
    }
}
