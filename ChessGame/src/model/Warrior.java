package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Warrior extends Arm {

    public Warrior(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Warrior";
        this.icon = ResourceManager.warrior_icon;

        this.max_hp += 300;
        this.hp += 300;
        this.armor += 30;

        this.max_move_time -= 15;
    }
}
