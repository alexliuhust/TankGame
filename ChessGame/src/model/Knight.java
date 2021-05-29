package model;

import frame.BattleField;
import resource.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Knight extends Arm {

    public int shieldProb = 1;
    public int shieldTime = 40;
    public boolean shieldActivated = false;
    private Random rand = new Random();

    public Knight(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Knight";
        this.icon = ResourceManager.knight_icon;

        this.max_hp += 100;
        this.hp += 100;
        this.armor += 20;
    }

    @Override
    public int getArmor() {
        if (shieldActivated) {
            return Math.min(this.armor + shieldProb, 100);
        }
        return this.armor;
    }

    @Override
    public void fieldUpdate(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(Color.CYAN);
        if (shieldTime > 0) {
            shieldActivated = true;
            g.drawOval(leftTop()[0] - 5, leftTop()[1] - 5, Width + 10, Height + 10);
            shieldTime--;
        }
        else {
            if (rand.nextInt(100) <= this.shieldProb) {
                shieldTime = 30;
                shieldActivated = true;
            } else {
                shieldTime = 0;
                shieldActivated = false;
            }
        }
        g.setColor(originalColor);
    }
}
