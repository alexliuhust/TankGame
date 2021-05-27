package action;

import model.Arm;

public class Attack {

    public static void normalAttack(Arm attacker, Arm defender) {
        defender.hp -= attacker.attack;
        if (defender.hp < 0) {
            defender.alive = false;
        }
    }

}
