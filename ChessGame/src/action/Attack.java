package action;

import effect.MeleeEffect;
import frame.BattleField;
import model.Arm;

public class Attack {

    /**
     * The normal attack action
     * @param attacker the arm that cause the normal damage
     * @param defender the arm that take the normal damage
     */
    public static void normalAttack(Arm attacker, Arm defender, BattleField bf) {
        if (attacker.att_time < attacker.max_att_time) {
            return;
        }

        bf.effects.add(new MeleeEffect(attacker, defender, attacker.armColor));

        attacker.att_time = 0;
        defender.hp -= attacker.attack;
        if (defender.hp < 0) {
            defender.alive = false;
        }
    }

}
