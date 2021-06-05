package effect;

import model.Arm;

import java.awt.*;

public class HealingEffect extends MeleeEffect {
    public HealingEffect(Arm caster, Arm target, Color effectColor) {
        super(caster, target, effectColor);
        this.lastTime = 5;
    }
}
