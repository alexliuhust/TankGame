package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Worrier extends Arm {

    public Worrier(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.icon = ResourceManager.warrior_icon;
    }
}
