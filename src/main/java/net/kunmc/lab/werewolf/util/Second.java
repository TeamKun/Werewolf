package net.kunmc.lab.werewolf.util;

public class Second {
    private int second;

    public Second(int second) {
        this.second = second;
    }

    public int milliSecond() {
        return this.second * 1000;
    }

    public int tick() {
        return this.second * 20;
    }
}
