package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MeleeEffect extends Effect {

    private Arm attacker, defender;
    private final Map<String, int[]> startPosition = new HashMap<>() {{
        put("-1_-1", new int[]{0, -20});
        put("0_-1", new int[]{20, -20});
        put("1_-1", new int[]{20, 0});
        put("1_0", new int[]{20, 20});
        put("1_1", new int[]{0, 20});
        put("0_1", new int[]{-20, 20});
        put("1-_1", new int[]{-20, 0});
        put("1-_0", new int[]{-20, -20});
    }};

    public MeleeEffect(Arm attacker, Arm defender, Color effectColor) {
        super();
        this.attacker = attacker;
        this.defender = defender;
        this.lastTime = 10;
        this.effectColor = effectColor;
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (this.lastTime <= 0) {
            bf.effects.remove(this);
            return;
        }
        this.lastTime--;

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        int x1 = attacker.central()[0];
        int y1 = attacker.central()[1];
        int x2_target = defender.central()[0];
        int y2_target = defender.central()[1];

        int keyX = 0;
        if (defender.x < attacker.x) keyX = -1;
        else if (defender.x > attacker.x) keyX = 1;

        int keyY = 0;
        if (defender.y < attacker.y) keyY = -1;
        else if (defender.y > attacker.y) keyY = 1;

        String key = keyX + "_" + keyY;
        if (startPosition.containsKey(key)) {
            x1 += startPosition.get(key)[0];
            y1 += startPosition.get(key)[1];
        }

        int[] lengths = {2,2,4,4,6,6,4,4,2,2,2};
        int relativeLength = lengths[lastTime];
        int x2 = x1 + (x2_target - x1) * relativeLength / 6;
        int y2 = y1 + (y2_target - y1) * relativeLength / 6;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        g.drawLine(x1 + 1, y1 - 1, x2 + 1, y2 - 1);
        g.drawLine(x1 - 1, y1 + 1, x2 - 1, y2 + 1);
        g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);

        g.setColor(originalColor);
    }
}
