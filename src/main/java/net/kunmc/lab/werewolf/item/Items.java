package net.kunmc.lab.werewolf.item;

import org.bukkit.entity.Player;

public enum Items {
    INVISIBLE_POTION("透視ポーション", new InvisiblePotion(), ItemType.PORTION),
    INVINCIBLE_POTION("無敵ポーション", new InvinciblePotion(), ItemType.PORTION),
    SMOKE_BALL("煙玉", new SmokeBall(), ItemType.NORMAL),
    SHUFFLE_STAR("シャッフルスター", new ShuffleStar(), ItemType.NORMAL);

    String displayName;
    SpecialItem instance;
    ItemType itemType;

    Items(String displayName, SpecialItem instance, ItemType itemType) {
        this.displayName = displayName;
        this.instance = instance;
        this.itemType = itemType;
    }

    public static boolean use(String displayName, Player user, ItemType itemType) {
        for (Items item : Items.values()) {
            if (item.displayName.equals(displayName) && item.itemType.equals(itemType)) {
                item.instance.use(user);
                return true;
            }
        }

        return false;
    }
}
