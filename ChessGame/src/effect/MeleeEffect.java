package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;

public class MeleeEffect extends Effect {

    private Arm attacker, defender;

    public MeleeEffect(Arm attacker, Arm defender, Color effectColor) {
        super();
        this.attacker = attacker;
        this.defender = defender;
        this.lastTime = 10;
        this.effectColor = effectColor;
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (this.lastTime <= 0) {
            bf.effects.remove(this);
            return;
        }
        this.lastTime--;

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        int x1 = attacker.central()[0];
        int y1 = attacker.central()[1];
        int x2_target = defender.central()[0];
        int y2_target = defender.central()[1];

        int[] lengths = {2,2,4,4,6,6,4,4,2,2,2};
        int relativeLength = lengths[lastTime];
        int x2 = x1 + (x2_target - x1) * relativeLength / 6;
        int y2 = y1 + (y2_target - y1) * relativeLength / 6;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        g.drawLine(x1 + 1, y1 - 1, x2 + 1, y2 - 1);
        g.drawLine(x1 - 1, y1 + 1, x2 - 1, y2 + 1);
        g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);

        g.setColor(originalColor);
    }
}
