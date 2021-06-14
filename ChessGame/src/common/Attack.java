package common;

import effect.BombardEffect;
import effect.MeleeEffect;
import effect.RangeEffect;
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

        // Melee
        if (attacker.range <= 1) {
            bf.effects.add(new MeleeEffect(attacker, defender, attacker.armColor));
            attacker.att_time = 0;
            defender.hp -= calculateDamage(attacker, defender);
            if (defender.hp < 0) {
                defender.alive = false;
            }
        }

        // Range
        else {
            if (!attacker.career.equals("Artillery")) {
                bf.effects.add(new RangeEffect(attacker, defender, attacker.armColor));
            } else {
                bf.effects.add(new BombardEffect(attacker, defender, attacker.armColor));
            }
            attacker.att_time = 0;
        }
    }

    public static int calculateDamage(Arm attacker, Arm defender) {
        int damage;

        if (!attacker.isMagic) {
            damage = attacker.getAttack() * (100 - defender.getArmor()) / 100;
        }
        else {
            damage = attacker.getAttack() * (100 - defender.getMagicResistance()) / 100;
        }

        return damage;
    }

}
