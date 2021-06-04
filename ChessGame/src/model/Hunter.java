package model;

import common.Attack;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Hunter extends Arm {

    private Arm preTarget = null;
    private int org_max_att_time;

    public Hunter(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Hunter";
        this.icon = ResourceManager.hunter_icon;

        this.range += 3;

        this.max_att_time -= 5;

        this.preTarget = this.getTheClosestEnemy();
        this.org_max_att_time = this.max_att_time;
    }

    @Override
    public int getAttack() {
        Arm target = this.getTheClosestEnemy();
        if (target == this.preTarget) {
            this.max_att_time -= 3;
            this.max_att_time = Math.max(10, this.max_att_time);
        } else {
            this.max_att_time = this.org_max_att_time;
            this.preTarget = target;
        }
        return super.getAttack();
    }

    //    @Override
//    protected boolean tryToAttackTheClosestEnemy(Arm target) {
//        int distance = Math.max(Math.abs(target.x - x), Math.abs(target.y - y));
//        if (distance <= range) {
//            if (target == this.preTarget) {
//                this.max_att_time -= 1;
//                this.max_att_time = Math.max(2, this.max_att_time);
//            } else {
//                this.max_att_time = this.org_max_att_time;
//                this.preTarget = target;
//            }
//
//
//            Attack.normalAttack(this, target, this.bf);
//            return true;
//        }
//        return false;
//    }
}
