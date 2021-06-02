package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;

public class Mage extends Arm {
    public Mage(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Mage";
        this.icon = ResourceManager.mage_icon;

        this.max_hp -= 50;
        this.hp -= 50;
        this.magicResistance += 50;

        this.isMagic = true;
        this.range += 3;
    }
}
