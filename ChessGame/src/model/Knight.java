package model;

import frame.BattleField;
import resource.ResourceManager;
import java.awt.*;

public class Knight extends Arm {

    public int shieldProb = 1;
    public int shieldTime = 80;
    public boolean shieldActivated = false;

    public Knight(int x, int y, Color armColor, BattleField bf) {
        super(x, y, armColor, bf);
        this.career = "Knight";
        this.icon = ResourceManager.knight_icon;

        this.max_hp += 100;
        this.hp += 100;
        this.armor += 25;
        this.magicResistance += 25;
    }

    @Override
    public int getArmor() {
        if (shieldActivated) {
            return Math.min(this.armor + 55 + rand.nextInt(25), 100);
        }
        return Math.min(100, this.armor);
    }

    @Override
    public int getMagicResistance() {
        if (shieldActivated) {
            return Math.min(this.magicResistance + 55 + rand.nextInt(25), 100);
        }
        return Math.min(100, this.magicResistance);
    }

    @Override
    public void fieldUpdate(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(Color.CYAN);

        // The shield is on
        if (shieldTime > 0) {
            shieldActivated = true;
            if (shieldTime >= 10) {
                g.drawOval(leftTop()[0] - 4, leftTop()[1] - 4, Width + 8, Height + 8);
            }
            if (shieldTime >= 20) {
                g.drawOval(leftTop()[0] - 5, leftTop()[1] - 5, Width + 10, Height + 10);
            }
            if (shieldTime >= 30) {
                g.drawOval(leftTop()[0] - 6, leftTop()[1] - 6, Width + 12, Height + 12);
            }
            shieldTime--;
        }

        // Randomly generate shield
        else {
            if (shieldTime == 0) {
                if (rand.nextInt(100) <= this.shieldProb) {
                    shieldTime = rand.nextInt(30) + 30;
                    shieldActivated = true;
                } else {
                    shieldTime = rand.nextInt(20) - 30;
                    shieldActivated = false;
                }
            } else {
                shieldTime++;
            }
        }
        g.setColor(originalColor);
    }
}
