package action;

import model.Arm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerBonus {

    public static void careerBonus(List<Arm> arms) {
        Map<String, List<Arm>> map = new HashMap<>();
        for (Arm arm : arms) {
            List<Arm> list = map.getOrDefault(arm.career, new ArrayList<>());
            list.add(arm);
            map.put(arm.career, list);
        }

        // Warrior
        if (map.containsKey("Warrior") && !map.get("Warrior").isEmpty()) {
            int level = map.get("Warrior").size() / 2;
            for (Arm arm : map.get("Warrior")) {
                arm.max_hp += level * 150;
                arm.hp += level * 150;
            }
        }

        // Knight
        if (map.containsKey("Knight") && !map.get("Knight").isEmpty()) {
            int level = map.get("Knight").size() / 2;
            for (Arm arm : map.get("Knight")) {
                arm.armor += level * 15;
            }
        }

        // Hunter
        if (map.containsKey("Hunter") && !map.get("Hunter").isEmpty()) {
            int level = map.get("Hunter").size() / 2;
            for (Arm arm : map.get("Hunter")) {
                arm.max_att_time -= level * 5;
            }
        }

    }
}
