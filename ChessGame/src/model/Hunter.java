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
        this.max_att_time += 10;
        this.preTarget = this.getTheClosestEnemy();
        this.org_max_att_time = this.max_att_time;
    }

    @Override
    public int getAttack() {
        Arm target = this.getTheClosestEnemy();
        if (target == this.preTarget) {
            this.max_att_time -= 5;
            this.max_att_time = Math.max(12, this.max_att_time);
        } else {
            this.max_att_time = this.org_max_att_time;
            this.preTarget = target;
        }
        return super.getAttack();
    }

}
