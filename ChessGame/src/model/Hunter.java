package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Hunter extends Arm {

    public Hunter(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.icon = ResourceManager.hunter_icon;
        this.range += 4;
    }
}
