package model;

import effect.PhotosphereEffect;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Mage extends Arm {

    public int max_mana = 180;
    public int mana;
    private Random rand = new Random();

    public Mage(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Mage";
        this.icon = ResourceManager.mage_icon;
        this.mana = rand.nextInt(20);

        this.max_hp -= 50;
        this.hp -= 50;
        this.magicResistance += 10;

        this.attack = 2;
        this.isMagic = true;
        this.range += 3;
    }

    @Override
    public int getArmor() {
        this.mana += 2;
        return super.getArmor();
    }

    @Override
    public int getMagicResistance() {
        this.mana += 2;
        return super.getMagicResistance();
    }

    @Override
    public void castSkill(BattleField bf) {
        if (mana >= max_mana) {
            mana = 0;
            Arm target = this.getTheClosestEnemy();
            this.bf.effects.add(new PhotosphereEffect(this, target, this.armColor));
            return;
        }
        mana++;
    }
}
