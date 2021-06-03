package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BigHitEffect extends Effect {

    private List<Arm> enemies = new ArrayList<>();
    private int central_x, central_y;
    private boolean alive = true;

    public BigHitEffect(Arm caster, List<Arm> enemies) {
        this.enemies = enemies;
        this.lastTime = 10;
        this.effectColor = caster.armColor;
        this.central_x = caster.central()[0];
        this.central_y = caster.central()[1];
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (lastTime <= 0) {
            this.alive = false;
            return;
        }

        lastTime--;
        int width = 120 - 4 * lastTime;
        int x = central_x - width / 2;
        int y = central_y - width / 2;

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        g.drawOval(x, y, width, width);
        g.drawOval(x + 1, y + 1, width - 2, width - 2);
        g.drawOval(x + 2, y + 2, width - 4, width - 4);

        g.setColor(originalColor);
    }
}
