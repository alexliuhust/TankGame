package effect;

import common.Attack;
import frame.BattleField;
import model.Arm;

import java.awt.*;

public class RangeEffect extends Effect {

    public int x, y;
    public int Width = 10, Height = 10;
    public double speed = 10.0;
    public Arm attacker;
    public Arm defender;

    public Color effectColor;

    public boolean alive = true;

    public RangeEffect(Arm attacker, Arm defender, Color effectColor) {
        this.attacker = attacker;
        this.defender = defender;

        this.x = attacker.central()[0] - Width / 2;
        this.y = attacker.central()[1] - Height / 2;
        this.effectColor = effectColor;
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (!alive) {
            bf.effects.remove(this);
        }

        Color originalColor = g.getColor();
        g.setColor(effectColor);

        g.fillOval(x, y, this.Width, this.Height);
        g.setColor(originalColor);

        moveToTarget();
    }

    protected void moveToTarget() {
        Rectangle r1 = new Rectangle(x, y, this.Width, this.Height);
        Rectangle r2 = new Rectangle(defender.leftTop()[0], defender.leftTop()[1], Arm.Width, Arm.Height);
        if (r1.intersects(r2)) {
            defender.hp -= Attack.calculateDamage(attacker, defender);
            if (defender.hp < 0) {
                defender.alive = false;
            }
            this.alive = false;
            return;
        }

        int relative_x = defender.central()[0] - (x);
        int relative_y = defender.central()[1] - (y);
        int[] ans = new int[2];
        if (relative_x != 0) {
            double radian = Math.atan((0.0 + relative_y) / relative_x);
            ans[0] = (int) Math.round(speed * Math.cos(radian));
            ans[1] = (int) Math.round(speed * Math.sin(radian));
            if (relative_x < 0) {
                ans[0] = -ans[0];
                ans[1] = -ans[1];
            }
        } else {
            ans[1] = (int) speed;
        }

        this.x += ans[0];
        this.y += ans[1];
    }
}
