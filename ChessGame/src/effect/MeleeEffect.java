package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;

public class MeleeEffect extends Effect {

    public MeleeEffect(int x, int y, Color effectColor) {
        super();
        this.x = x;
        this.y = y;
        this.lastTime = 10;
        this.effectColor = effectColor;
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (this.lastTime <= 0) {
            bf.effects.remove(this);
        }
        this.lastTime--;

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        int posX = this.x * 60 + 50 + (60 - Arm.Width) / 2;
        int posY = this.y * 60 + 100 + (60 - Arm.Width) / 2 + Arm.Width / 2;

        g.fillRect(posX, posY, 40, 4);

        g.setColor(originalColor);
    }
}
