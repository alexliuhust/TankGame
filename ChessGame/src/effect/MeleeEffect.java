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
        }
        this.lastTime--;

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        int x1 = attacker.central()[0];
        int y1 = attacker.central()[1];
        int x2 = defender.central()[0];
        int y2 = defender.central()[1];
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        g.drawLine(x1 + 1, y1 - 1, x2 + 1, y2 - 1);
        g.drawLine(x1 - 1, y1 + 1, x2 - 1, y2 + 1);
        g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);

        g.setColor(originalColor);
    }
}
