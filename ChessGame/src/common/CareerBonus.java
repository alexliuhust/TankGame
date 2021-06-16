package common;

import model.*;

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

            if (career.equals("Artillery")) {
                for (Arm arm : enemies) {
                    arm.armor -= level * 10;
                }
            }

            for (Arm arm : map.get(career)) {
                switch (career) {
                    case "Warrior":
                        Warrior warrior = (Warrior) arm;
                        warrior.max_hp += level * 100;
                        warrior.hp += level * 100;
                        warrior.attack += level * 5;
                        break;

                    case "Knight":
                        Knight knight = (Knight) arm;
                        knight.shieldProb += level * 20;
                        knight.shieldTime += 15;
                        break;

                    case "Hunter":
                        arm.max_att_time -= level * 2;
                        break;

                    case "Mage":
                        Mage mage = (Mage) arm;
                        mage.attack += level * 5;
                        mage.magicResistance += level * 10;
                        mage.mana += level * 40;
                        break;

                    case "Priest":
                        Priest priest = (Priest) arm;
                        priest.max_healingStorage += level * 50;
                        priest.healingStorage     += level * 50;
                        break;

                    case "Assassin":
                        Assassin assassin = (Assassin) arm;
                        assassin.strikeProb += level * 18;
                        assassin.max_att_time -= level * 2;
                        assassin.dodgeProb += level * 1.5;
                        break;

                    default:
                        break;
                }
            }
        }
    }

}
