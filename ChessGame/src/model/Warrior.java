package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Warrior extends Arm {

    public Warrior(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Warrior";
        this.icon = ResourceManager.warrior_icon;

        this.max_hp += 200;
        this.hp += 200;
        this.armor += 5;

        this.max_move_time -= 15;

        this.attack -= 20;
    }
}
