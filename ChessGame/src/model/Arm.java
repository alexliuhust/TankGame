package model;

import action.Move;
import frame.BattleField;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.PriorityQueue;

public class Arm {

    private BattleField bf;
    private List<Arm> enemies;

    public int x, y;
    public int Width = 50, Height = 50;
    public Color armColor;

    public boolean alive = true;
    public int max_hp = 100;
    public int hp = max_hp;

    public int max_move_time = 10;
    public int move_time = 0;

    public int attack = 20;
    public int range = 1;
    public int max_att_time = 40;
    public int att_time = max_att_time;

    public String type = "Worrier";

    public Arm(int x, int y, Color armColor, BattleField bf) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.armColor = armColor;

        if (this.armColor.equals(Color.RED)){
            this.enemies = bf.blue_arms;
        } else {
            this.enemies = bf.red_arms;
        }
    }

    public void paint(Graphics g) {
        moveToTheClosestEnemy();

        Color originalColor = g.getColor();
        g.setColor(armColor);
        g.fillRect(this.x * 60 + 55, this.y * 60 + 105, this.Width, this.Height);
        g.setColor(originalColor);
    }

    private void moveToTheClosestEnemy() {
        //==========================
        if (!this.armColor.equals(Color.BLUE)) {
            return;
        }
        //============================

        // Get the position of the closest enemy
        if (enemies.isEmpty()) return;
        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            int dis_a = Math.abs(x - a.x) + Math.abs(y - a.y);
            int dis_b = Math.abs(x - b.x) + Math.abs(y - b.y);
            return dis_a - dis_b;
        });
        pq.addAll(this.enemies);

        int target_x = pq.peek().x;
        int target_y = pq.peek().y;
        int distance = Math.max(Math.abs(target_x - x), Math.abs(target_y - y));
        if (distance <= range) return;

        // Move one step to the target
        if (this.move_time < this.max_move_time) {
            this.move_time++;
            return;
        }
        this.move_time = 0;
        int[] next_step = Move.getNextStep(x, y, target_x, target_y, bf.board);

        this.x = next_step[0];
        this.y = next_step[1];
    }

}
