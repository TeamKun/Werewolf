package net.kunmc.lab.werewolf.actor;

class SkillPoint {
    private int max;
    private int current;

    public SkillPoint(int max) {
        if (max < 0) {
            max = 0;
        }
        this.max = max;
        this.current = max;
    }

    public boolean canUse() {
        return current != 0;
    }

    public int useSkill() {
        if (this.current <= 0) {
            return 0;
        }
        current--;
        return current;
    }

    public int max() {
        return this.max;
    }

    public int current() {
        return this.current;
    }
}
