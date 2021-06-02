package model;

import common.Attack;
import common.Move;
import frame.BattleField;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Arm {

    protected BattleField bf;
    protected List<Arm> enemies;
    protected List<Arm> comrades;
    private Arm target;

    public int x, y;
    public static int Width = 46, Height = 46;
    public Color armColor;
    public BufferedImage icon;

    public boolean alive = true;
    public int max_hp = 200;
    public int hp = max_hp;
    public int armor = 0;

    public int max_move_time = 40;
    public int move_time;

    public int attack = 40;
    public int range = 1;

    public int max_att_time = 40;
    public int att_time;

    public String career;

    public Arm(int x, int y, Color armColor, BattleField bf) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.armColor = armColor;
        Random rand = new Random();
        this.move_time = rand.nextInt(max_move_time / 4);
        this.att_time = rand.nextInt(max_att_time);

        if (this.armColor.equals(Color.RED)){
            this.enemies = bf.blue_arms;
            this.comrades = bf.red_arms;
        } else {
            this.enemies = bf.red_arms;
            this.comrades = bf.blue_arms;
        }
    }

    public int getArmor() {
        return this.armor;
    }

    public int getAttack() {
        return this.attack;
    }

    public void paint(Graphics g) {
        if (!this.alive) {
            this.bf.board[y][x] = false;
            this.comrades.remove(this);
            return;
        }
        this.bf.board[y][x] = true;
        fieldUpdate(g);
        moveOrAttack();

        Color originalColor = g.getColor();
        g.setColor(armColor);
        g.fillRect(leftTop()[0], leftTop()[1], Width, Height);
        g.drawImage(icon, leftTop()[0] + 3, leftTop()[1] + 3, null);
        paintHpBar(g);
        g.setColor(originalColor);
    }

    public void fieldUpdate(Graphics g) {}

    /**
     * Get the left-top position
     */
    public int[] leftTop() {
        int[] pos = new int[2];
        pos[0] = this.x * 60 + 50 + (60 - Width) / 2;
        pos[1] = this.y * 60 + 100 + (60 - Height) / 2;
        return pos;
    }

    /**
     * Get the central position
     */
    public int[] central() {
        int[] pos = new int[2];
        pos[0] = this.x * 60 + 50 + (60 - Width) / 2 + Width / 2;
        pos[1] = this.y * 60 + 100 + (60 - Height) / 2 + Height / 2;
        return pos;
    }

    /**
     * Draw the HP bar
     */
    private void paintHpBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(leftTop()[0] + 3, leftTop()[1] + 3, Width - 6, 5);
        g.setColor(Color.GREEN);
        g.fillRect(leftTop()[0] + 3, leftTop()[1] + 3, Width * (hp * 100 / max_hp) / 100 - 6, 5);

        g.setColor(c);
    }

    /**
     * Find the closest enemy and try to attack it.
     * If it is out of the range, try to move to it.
     */
    private void moveOrAttack() {
        this.att_time++;
        this.move_time++;

        this.target = getTheClosestEnemy();
        if (target == null) return;
        // If the closest enemy is within the range, attack it
        if (tryToAttackTheClosestEnemy(target)) return;

        // Move one step to the target
        if (this.move_time < this.max_move_time) return;
        this.move_time = 0;
        int[] next_pos = this.getNextPosition();

        this.bf.board[y][x] = false;
        this.x = next_pos[0];
        this.y = next_pos[1];
        this.bf.board[y][x] = true;
    }

    /**
     * Get the next position
     */
    protected int[] getNextPosition() {
        return Move.normalMove(x, y, target.x, target.y, bf.board);
    }

    /**
     * Try to attack the closest enemy
     */
    private boolean tryToAttackTheClosestEnemy(Arm target) {
        // If the closest enemy is within the range, attack it
        int distance = Math.max(Math.abs(target.x - x), Math.abs(target.y - y));
        if (distance <= range) {
            Attack.normalAttack(this, target, this.bf);
            return true;
        }
        return false;
    }

    /**
     * Get the closest enemy
     */
    private Arm getTheClosestEnemy() {
        // Get the position of the closest enemy
        if (enemies.isEmpty()) {
            return null;
        }

        int minDis = Integer.MAX_VALUE;
        Arm closest = null;
        for (Arm arm : enemies) {
            int dis = (x - arm.x) * (x - arm.x) + (y - arm.y) * (y - arm.y);
            if (dis < minDis) {
                minDis = dis;
                closest = arm;
            }
        }

        return closest;
    }

    /**
     * Use the skills
     */
    public void castSkill(Arm caster, Arm target, BattleField bf) {}
}
