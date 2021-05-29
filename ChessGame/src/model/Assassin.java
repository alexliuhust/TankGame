package model;

import action.Move;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Assassin extends Arm {

    private Random rand = new Random();

    public int strikeProb = 18;

    public Assassin(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Assassin";
        this.icon = ResourceManager.assassin_icon;

        this.max_move_time -= 10;

        this.attack += 20;
    }

    @Override
    public int getAttack() {
        int r = rand.nextInt(100);

        int damage = this.attack;
        if (r <= strikeProb) {
            damage *= 2;
        }
        if (r <= strikeProb / 3 * 2) {
            damage *= 2;
        }
        if (r <= strikeProb / 3) {
            damage *= 2;
        }
//        System.out.println(damage);
        return damage;
    }

    @Override
    public int getArmor() {
        int r = rand.nextInt(100);
        if (r <= 20) {
            return 100;
        }
        return this.armor;
    }

    @Override
    protected int[] getNextPosition() {
        Arm farthest = this.getTheFarthestEnemy();
        if (farthest == null) return new int[] {x, y};
        return Move.assassinJump(x, y, farthest.x, farthest.y, this.bf.board);
    }

    /**
     * Get the farthest enemy
     */
    private Arm getTheFarthestEnemy() {
        // Get the position of the farthest enemy
        if (enemies.isEmpty()) {
            return null;
        }

        int maxDis = Integer.MIN_VALUE;
        Arm farthest = null;
        for (Arm arm : enemies) {
            int dis = Math.abs(x - arm.x) + Math.abs(y - arm.y);
            if (dis > maxDis) {
                maxDis = dis;
                farthest = arm;
            }
        }

        return farthest;
    }
}
