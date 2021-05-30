package model;

import action.Move;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Assassin extends Arm {

    private Random rand = new Random();

    public int strikeProb = 18;

    public Assassin(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Assassin";
        this.icon = ResourceManager.assassin_icon;

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
        if (r <= 15) {
            return 100;
        }
        return this.armor;
    }

    @Override
    protected int[] getNextPosition() {
        if (rand.nextInt(3) < 1) {
            return super.getNextPosition();
        }

        List<int[]> availablePops = getAvailablePositions();
        if (availablePops == null || availablePops.size() == 0) {
            return new int[] {x, y};
        }
        return availablePops.get(rand.nextInt(availablePops.size()));
    }

    /**
     * Get available surrounding positions for the highest-priority target
     */
    private List<int[]> getAvailablePositions() {
        if (enemies.isEmpty()) {
            return null;
        }

        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            if (a.range != b.range) {
                return b.range - a.range;
            }
            int da = (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
            int db = (x - b.x) * (x - b.x) + (y - b.y) * (y - b.y);
            return db - da;
        });
        pq.addAll(enemies);

        for (Arm arm : pq) {
            List<int[]> availableCells = getAvailableCells(arm.x, arm.y);
            if (availableCells.size() == 0) {
                continue;
            }
            return availableCells;
        }
        return null;
    }

    /**
     * Get all available cells around the target
     */
    private List<int[]> getAvailableCells(int target_x, int target_y) {
        List<int[]> availableCells = new ArrayList<>();

        int[] dir = {0, -1, 0, 1, 0};
        for (int k = 0; k < 4; k++) {
            int x = target_x + dir[k];
            int y = target_y + dir[k + 1];
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && !bf.board[y][x]) {
                availableCells.add(new int[] {x, y});
            }
        }
        return availableCells;
    }
}
