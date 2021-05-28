package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Knight extends Arm {

    public Knight(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Knight";
        this.icon = ResourceManager.knight_icon;

        this.max_hp += 100;
        this.hp += 100;
        this.armor += 40;
    }

    @Override
    public int getArmor() {
        Random rand = new Random();
        return this.armor - rand.nextInt(this.armor / 2);
    }
}
