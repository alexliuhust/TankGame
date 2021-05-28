package model;

import action.Attack;
import action.Move;
import frame.BattleField;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.PriorityQueue;

public class Arm {

    private BattleField bf;
    private List<Arm> enemies;
    private List<Arm> comrades;

    public int x, y;
    public int Width = 50, Height = 50;
    public Color armColor;

    public boolean alive = true;
    public int max_hp = 300;
    public int hp = max_hp;

    public int max_move_time = 20;
    public int move_time = 0;

    public int attack = 40;
    public int range = 1;

    public int max_att_time = 30;
    public int att_time = max_att_time;

    public String type = "Worrier";

    public Arm(int x, int y, Color armColor, BattleField bf) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.armColor = armColor;

        if (this.armColor.equals(Color.RED)){
            this.enemies = bf.blue_arms;
            this.comrades = bf.red_arms;
        } else {
            this.enemies = bf.red_arms;
            this.comrades = bf.blue_arms;
        }
    }

    public void paint(Graphics g) {
        if (!this.alive) {
            this.comrades.remove(this);
        }

        moveOrAttack();

        Color originalColor = g.getColor();
        g.setColor(armColor);
        g.fillRect(this.x * 60 + 55, this.y * 60 + 105, this.Width, this.Height);
        paintHpBar(g);
        g.setColor(originalColor);
    }

    /**
     * Draw the HP bar
     */
    private void paintHpBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(this.x * 60 + 55, this.y * 60 + 105, Width, 3);
        g.setColor(Color.GREEN);
        g.fillRect(this.x * 60 + 55, this.y * 60 + 105, Width * (hp * 100 / max_hp) / 100, 3);

        g.setColor(c);
    }

    /**
     * Find the closest enemy and try to attack it.
     * If it is out of the range, try to move to it.
     */
    private void moveOrAttack() {
        this.att_time++;
        this.move_time++;

        Arm target = getTheClosestEnemy();
        if (target == null) return;

        // If the closest enemy is within the range, attack it
        int distance = Math.max(Math.abs(target.x - x), Math.abs(target.y - y));
        if (distance <= range) {
            Attack.normalAttack(this, target);
            return;
        }

        // Move one step to the target
        if (this.move_time < this.max_move_time) {
            return;
        }
        this.move_time = 0;
        int[] next_step = Move.getNextStep(x, y, target.x, target.y, bf.board);

        this.x = next_step[0];
        this.y = next_step[1];
    }

    /**
     * Get the closest enemy
     */
    private Arm getTheClosestEnemy() {
        // Get the position of the closest enemy
        if (enemies.isEmpty()) {
            return null;
        }

        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            int dis_a = Math.abs(x - a.x) + Math.abs(y - a.y);
            int dis_b = Math.abs(x - b.x) + Math.abs(y - b.y);
            return dis_a - dis_b;
        });
        pq.addAll(this.enemies);

        return pq.poll();
    }

}
