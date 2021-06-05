package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BigHitEffect extends Effect {
    private Random rand = new Random();

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
        this.paintEffect(g, x, y, width);
        this.enemiesGetHit(x, y, width);
    }

    private void paintEffect(Graphics g, int x, int y, int R) {
        Color originalColor = g.getColor();
        g.setColor(effectColor);
        g.drawOval(x, y, R, R);
        g.drawOval(x + 1, y + 1, R - 2, R - 2);
        g.drawOval(x + 2, y + 2, R - 4, R - 4);

        g.setColor(originalColor);
    }

    private void enemiesGetHit(int x, int y, int R) {
        Rectangle r1 = new Rectangle(x, y, R, R);
        for (Arm arm : enemies) {
            Rectangle r2 = new Rectangle(arm.leftTop()[0], arm.leftTop()[1], Arm.Width, Arm.Height);
            if (r1.intersects(r2)) {
                arm.armor -= rand.nextInt(10) < 2? 1 : 0;
                int damage = 4;
                arm.hp -= damage * (100 - arm.getArmor()) / 100;
                if (arm.hp < 0) {
                    arm.alive = false;
                }
            }
        }
    }
}
