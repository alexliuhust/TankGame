package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Artillery extends Arm {
    public Artillery(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Artillery";
        this.icon = ResourceManager.artillery_icon;

        this.max_hp -= 100;
        this.hp     -= 100;

        this.max_move_time += 40;

        this.attack += 15;
        this.range += 4;

        this.max_att_time += 40;
    }
}
