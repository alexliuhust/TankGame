package model;

import common.Move;
import frame.BattleField;
import resource.ResourceManager;
import java.awt.*;
import java.util.List;

public class Assassin extends Arm {

    public int strikeProb = 18;
    public int dodgeProb = 5;

    public Assassin(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Assassin";
        this.icon = ResourceManager.assassin_icon;

        this.move_time = rand.nextInt(this.max_move_time / 3);
    }

    @Override
    public int getAttack() {
        int r = rand.nextInt(100);

        int damage = this.attack;
        if (r <= strikeProb)            damage *= 2;
        if (r <= strikeProb / 3 * 2)    damage *= 2;
        if (r <= strikeProb / 3)        damage *= 2;
        return damage;
    }

    @Override
    public int getArmor() {
        int r = rand.nextInt(100);
        if (r <= dodgeProb) return 100;
        return this.armor;
    }

    @Override
    protected int[] getNextPosition() {
        if (rand.nextInt(7) < 1) {
            return super.getNextPosition();
        }

        List<int[]> availablePops = Move.getAvailablePositionsForAssassins(x, y, bf, enemies);
        if (availablePops == null || availablePops.size() == 0) {
            return new int[] {x, y};
        }
        return availablePops.get(rand.nextInt(availablePops.size()));
    }
}
