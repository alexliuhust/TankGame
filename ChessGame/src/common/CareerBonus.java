package common;

import model.Arm;
import model.Assassin;
import model.Knight;
import model.Mage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerBonus {

    public static void careerBonus(List<Arm> arms, List<Arm> enemies) {
        Map<String, List<Arm>> map = new HashMap<>();
        for (Arm arm : arms) {
            List<Arm> list = map.getOrDefault(arm.career, new ArrayList<>());
            list.add(arm);
            map.put(arm.career, list);
        }

        for (String career : map.keySet()) {
            int level = map.get(career).size() / 2;
            if (career.equals("Mage")) {
                for (Arm arm : enemies) {
                    arm.magicResistance -= level * 10;
                }
            }

            for (Arm arm : map.get(career)) {
                switch (career) {
                    case "Warrior":
                        arm.max_hp += level * 100;
                        arm.hp += level * 100;
                        arm.attack += level * 5;
                        if (level >= 2) {
                            arm.armor += 2 * level;
                        }
                        break;

                    case "Knight":
                        Knight knight = (Knight) arm;
                        knight.shieldProb += level * 20;
                        knight.shieldTime += 15;
                        break;

                    case "Hunter":
                        arm.max_att_time -= level * 4;
                        break;

                    case "Mage":
                        Mage mage = (Mage) arm;
                        mage.attack += level * 5;
                        mage.magicResistance += level * 5;
                        break;

                    case "Assassin":
                        Assassin assassin = (Assassin) arm;
                        assassin.strikeProb += level * 9;
                        assassin.max_att_time -= level;
                        assassin.dodgeProb += level * 1.5;
                        break;

                    default:
                        break;
                }
            }
        }
    }

}
