package common;

import effect.PhotosphereEffect;
import frame.BattleField;
import model.Arm;

public class Skill {

    public static void magePhotosphere(Arm caster, Arm target, BattleField bf) {
        bf.effects.add(new PhotosphereEffect(caster, target, caster.armColor));

    }
}
