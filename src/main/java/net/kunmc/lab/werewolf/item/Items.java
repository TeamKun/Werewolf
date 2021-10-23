package net.kunmc.lab.werewolf.item;

import org.bukkit.entity.Player;

public enum Items {
    INVISIBLE_PORTION("透視ポーション", new InvisiblePortion()),
    INVINCIBLE_PORTION("無敵ポーション", new InvinciblePortion()),
    SMOKE_BALL("煙玉", new SmokeBall()),
    SHUFFLE_STAR("シャッフルスター", new ShuffleStar());

    String displayName;
    SpecialItem instance;

    Items(String displayName, SpecialItem instance) {
        this.displayName = displayName;
        this.instance = instance;
    }

    public static void use(String displayName, Player user) {
        for (Items item : Items.values()) {
            if (item.displayName.equals(displayName)) {
                item.instance.use(user);
            }
        }
    }
}
