package effect;

import model.Arm;

import java.awt.*;

public class BombardEffect extends RangeEffect {
    public BombardEffect(Arm attacker, Arm defender, Color effectColor) {
        super(attacker, defender, effectColor);
        this.Width = 20;
        this.Height = 20;
    }
}
