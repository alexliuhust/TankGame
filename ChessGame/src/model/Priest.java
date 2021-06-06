package model;

import common.Move;
import effect.HealingEffect;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.PriorityQueue;

public class Priest extends Arm {

    public int healing = 4;
    public int max_healingStorage = 200;
    public int healingStorage = max_healingStorage;

    public Priest(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Priest";
        this.icon = ResourceManager.priest_icon;

        this.max_hp -= 100;
        this.hp     -= 100;

        this.max_move_time -= 15;
        this.magicResistance += 50;

        this.attack = 0;
        this.range = 0;
    }

    @Override
    protected void paintHpBar(Graphics g) {
        super.paintHpBar(g);
        Color c = g.getColor();
        g.setColor(Color.CYAN);
        g.fillRect(leftTop()[0] + 3, leftTop()[1] + 8,
                Width * (healingStorage * 100 / max_healingStorage) / 100 - 6, 5);

        g.setColor(c);
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    protected boolean tryToAttackTheClosestEnemy(Arm target) {
        return false;
    }

    @Override
    protected int[] getNextPosition() {
        Arm target = this.getTheClosestComrade();
        if (target == null || Math.max(Math.abs(target.x - x), Math.abs(target.y - y)) <= 2) {
            return new int[] {x, y};
        }

        return Move.normalMove(x, y, target.x, target.y, this.bf.board);
    }

    private Arm getTheClosestComrade() {
        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            int dis_a = (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
            int dis_b = (x - b.x) * (x - b.x) + (y - b.y) * (y - b.y);
            if (dis_a != dis_b) {
                return dis_a - dis_b;
            }
            return a.hp - b.hp;
        });
        for (Arm arm : this.comrades) {
            if (arm.hp < arm.max_hp && arm != this) {
                pq.add(arm);
            }
        }
        return  pq.isEmpty()? null : pq.peek();
    }

    @Override
    public void castSkill(BattleField bf) {
        Arm target = this.getTheClosestComrade();
        this.healingStorage += 2;
        this.healingStorage = Math.min(this.healingStorage, this.max_healingStorage);

        if (target == null || target.hp == target.max_hp) {
            return;
        }
        if (Math.max(Math.abs(target.x - x), Math.abs(target.y - y)) <= 2
                && this.healingStorage >= this.healing * 2) {
            this.healingStorage -= this.healing * 2;
            this.bf.effects.add(new HealingEffect(this, target, Color.GREEN));
            target.hp += this.healing;
            target.hp = Math.min(target.hp, target.max_hp);
        }
    }
}
