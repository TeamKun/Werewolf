package net.kunmc.lab.werewolf.command;

import net.kunmc.lab.werewolf.meta.AbilityMeta;
import net.kunmc.lab.werewolf.meta.RoleMeta;

public class AbilityArgument<T> {
    private final AbilityMeta meta;
    private T value;

    public AbilityArgument(AbilityMeta meta, T arg) {
        this.meta = meta;
        this.value = arg;
    }

    public RoleMeta roleMeta() {
        return this.meta.roleMeta;
    }

    public T value() {
        return value;
    }
}
