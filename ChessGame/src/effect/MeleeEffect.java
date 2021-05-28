package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;

public class MeleeEffect extends Effect {

    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public MeleeEffect(Arm attacker, Arm defender, Color effectColor) {
        super();
        this.x1 = attacker.central()[0];
        this.y1 = attacker.central()[1];
        this.x2 = defender.central()[0];
        this.y2 = defender.central()[1];
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

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        g.drawLine(x1 + 1, y1 - 1, x2 + 1, y2 - 1);
        g.drawLine(x1 - 1, y1 + 1, x2 - 1, y2 + 1);
        g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);

        g.setColor(originalColor);
    }
}
