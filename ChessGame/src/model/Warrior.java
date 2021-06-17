package model;

import effect.BigHitEffect;
import frame.BattleField;
import resource.ResourceManager;
import java.awt.*;

public class Warrior extends Arm {
    public int max_mana = 210;
    public int mana;

    public Warrior(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Warrior";
        this.icon = ResourceManager.warrior_icon;

        this.max_hp += 200;
        this.hp +=     200;
        this.armor += 15;

        this.mana = max_mana / 2 + rand.nextInt(max_mana / 2);

        this.attack -= 30;
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
            Arm target = this.getTheClosestEnemy();
            if (target != null) {
                int distance = Math.max(Math.abs(target.x - x), Math.abs(target.y - y));
                if (distance <= range) {
                    mana = 0;
                    bf.effects.add(new BigHitEffect(this, this.enemies));
                }
            }
        }
        else {
            mana++;
        }
    }

}
