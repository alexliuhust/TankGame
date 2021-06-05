package model;

import common.Move;
import effect.HealingEffect;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.PriorityQueue;

public class Priest extends Arm {

    private int max_mana = 100;
    private int mana = max_mana;
    private int healing = 10;

    public Priest(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Priest";
        this.icon = ResourceManager.priest_icon;

        this.max_move_time -= 15;
        this.magicResistance += 75;

        this.attack = 0;
        this.range = 0;
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
        if (target == null) {
            return new int[] {x, y};
        }
        return Move.normalMove(x, y, target.x, target.y, this.bf.board);
    }

    private Arm getTheClosestComrade() {
        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            int dis_a = (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
            int dis_b = (x - b.x) * (x - a.x) + (y - a.y) * (y - b.y);
            if (dis_a != dis_b) {
                return dis_a - dis_b;
            }
            return a.hp - b.hp;
        });
        pq.addAll(this.comrades);
        return  pq.isEmpty()? null : pq.peek();
    }

    @Override
    public void castSkill(BattleField bf) {
        if (mana >= max_mana) {
            Arm target = this.getTheClosestComrade();
            if (target == null) {
                mana++;
                return;
            }
            if (Math.max(Math.abs(target.x - x), Math.abs(target.y - y)) <= 1) {
                this.bf.effects.add(new HealingEffect(this, target, Color.GREEN));
                target.hp += this.healing;
                target.hp = Math.max(target.hp, target.max_hp);
            }
        } else {
            mana++;
        }
    }
}
