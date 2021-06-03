package effect;

import frame.BattleField;
import model.Arm;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PhotosphereEffect extends RangeEffect {

    private int relative_x;
    private double radian;
    private List<Arm> enemies;

    public PhotosphereEffect(Arm attacker, Arm defender, Color effectColor) {
        super(attacker, defender, effectColor);
        this.speed = 8.0;
        this.Width = 85;
        this.Height = 85;
        this.x = attacker.central()[0] - Width / 2;
        this.y = attacker.central()[1] - Height / 2;
        this.enemies = attacker.enemies;

        int target_x = defender.central()[0] - Width / 2;
        int target_y = defender.central()[1] - Height / 2;
        this.relative_x = target_x - x;
        int relative_y = target_y - y;
        this.radian = Math.atan((0.0 + relative_y) / relative_x);
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (!alive) {
            bf.effects.remove(this);
        }

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        g.drawOval(x, y, this.Width, this.Height);
        g.drawOval(x + 20, y       , this.Width - 40, this.Height);
        g.drawOval(x, y + 20       , this.Width, this.Height - 40);
        g.drawOval(x + 2, y + 2, this.Width - 4, this.Height - 4);
        g.drawOval(x + 4, y + 4, this.Width - 8, this.Height - 8);
        g.setColor(originalColor);

        moveToTarget();
    }

    @Override
    protected void moveToTarget() {
        if (x <= 0 || x >= BattleField.GAME_WIDTH || y <= 0 || y >= BattleField.GAME_HEIGHT) {
            this.alive = false;
            return;
        }

        for (Arm arm : enemies) {
            Rectangle r1 = new Rectangle(x, y, this.Width, this.Height);
            Rectangle r2 = new Rectangle(arm.leftTop()[0], arm.leftTop()[1], Arm.Width, Arm.Height);
            if (r1.intersects(r2)) {
                int damage = 5;
                arm.hp -= damage * (100 - arm.getMagicResistance()) / 100;
                if (arm.hp < 0) {
                    arm.alive = false;
                }
            }
        }

        int[] ans = new int[2];
        ans[0] = (int) Math.round(speed * Math.cos(radian));
        ans[1] = (int) Math.round(speed * Math.sin(radian));
        if (relative_x < 0) {
            ans[0] = -ans[0];
            ans[1] = -ans[1];
        }
        this.x += ans[0];
        this.y += ans[1];
    }
}
