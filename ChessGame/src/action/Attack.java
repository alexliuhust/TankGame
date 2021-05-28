package action;

import model.Arm;

public class Attack {

    /**
     * The normal attack action
     * @param attacker the arm that cause the normal damage
     * @param defender the arm that take the normal damage
     */
    public static void normalAttack(Arm attacker, Arm defender) {
        if (attacker.att_time < attacker.max_att_time) {
            return;
        }
        attacker.att_time = 0;

        defender.hp -= attacker.attack;
        if (defender.hp < 0) {
            defender.alive = false;
        }
    }

}
