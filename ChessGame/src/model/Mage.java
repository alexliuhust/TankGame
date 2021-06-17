package model;

import effect.PhotosphereEffect;
import frame.BattleField;
import resource.ResourceManager;
import java.awt.*;

public class Mage extends Arm {

    public int max_mana = 180;
    public int mana;

    public Mage(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Mage";
        this.icon = ResourceManager.mage_icon;
        this.mana = rand.nextInt(20);

        this.max_hp -= 50;
        this.hp -= 50;
        this.magicResistance += 10;
        this.armor = -5;

        this.attack = 2;
        this.isMagic = true;
        this.range += 3;
    }

    @Override
    protected void paintHpBar(Graphics g) {
        super.paintHpBar(g);
        Color c = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(leftTop()[0] + 3, leftTop()[1] + 8,
                Width * (mana * 100 / max_mana) / 100 - 6, 5);
        g.setColor(c);
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
            if (target != null) {
                this.bf.effects.add(new PhotosphereEffect(this, target, this.armColor));
            }
            return;
        }
        mana++;
    }
}
