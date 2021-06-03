package model;

import effect.BigHitEffect;
import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Warrior extends Arm {
    private Random rand = new Random();
    public int max_mana = 150;
    public int mana;

    public Warrior(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Warrior";
        this.icon = ResourceManager.warrior_icon;

        this.max_hp += 200;
        this.hp += 200;
        this.armor += 5;

        this.mana = max_mana / 2 + rand.nextInt(max_mana / 2);

        this.max_move_time -= 15;

        this.attack -= 20;
    }

    @Override
    public void castSkill(BattleField bf) {
        if (mana == max_mana) {
            mana = 0;
            bf.effects.add(new BigHitEffect(this, this.enemies));
            return;
        }
        mana++;
    }
}
