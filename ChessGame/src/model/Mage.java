package model;

import common.Skill;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Mage extends Arm {

    public int max_mana = 180;
    public int mana = max_mana / 2;

    public Mage(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Mage";
        this.icon = ResourceManager.mage_icon;

        this.max_hp -= 50;
        this.hp -= 50;
        this.magicResistance += 50;

        this.attack -= 30;
        this.isMagic = true;
        this.range += 3;
    }

    @Override
    public void castSkill(BattleField bf) {
        if (mana == max_mana) {
            mana = 0;
            Arm target = this.getTheClosestEnemy();
            Skill.magePhotosphere(this, target, bf);
            return;
        }
        mana++;
    }
}
