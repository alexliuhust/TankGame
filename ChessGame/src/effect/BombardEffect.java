package effect;

import common.Attack;
import frame.BattleField;
import model.Arm;

import java.awt.*;

public class BombardEffect extends RangeEffect {
    public int target_x, target_y;
    private int relative_x;
    private double radian;

    private boolean isBombard = false;
    private int bombardingTime = 10;

    private double pre_distance = Double.MAX_VALUE;

    public BombardEffect(Arm attacker, Arm defender, Color effectColor) {
        super(attacker, defender, effectColor);
        this.speed = 7.0;
        this.Width = 20;
        this.Height = 20;
        this.target_x = defender.central()[0] - Width / 2;
        this.target_y = defender.central()[1] - Height / 2;
        this.relative_x = target_x - x;
        int relative_y = target_y - y;
        this.radian = Math.atan((0.0 + relative_y) / relative_x);

//        System.out.println(target_x + "," + target_y + ":" + defender.x + "," + defender.y);
    }

    @Override
    public void paint(Graphics g, BattleField bf) {
        if (!alive) {
            bf.effects.remove(this);
        }

        paintEffect(g);
        moveToTarget();
    }

    private void paintEffect(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(effectColor);

        if (isBombard) {
            g.drawOval(x - 30, y - 30, this.Width + 60, this.Height + 60);
            g.drawOval(x - 31, y - 31, this.Width + 62, this.Height + 62);
            g.drawOval(x - 29, y - 29, this.Width + 58, this.Height + 58);
        } else {
            g.fillOval(x, y, this.Width, this.Height);
        }
        g.setColor(originalColor);
    }

    @Override
    protected void moveToTarget() {
        double distance = Math.sqrt((x-target_x)*(x-target_x) + (y-target_y)*(y-target_y));

        if (distance <= this.pre_distance) {
            this.pre_distance = distance;
        } else {
            if (this.isBombard) {
                this.bombardingTime--;
                if (this.bombardingTime <= 0) {
                    this.alive = false;
                    return;
                }
                return;
            } else {
                this.isBombard = true;
                enemiesGetHit();
            }
            return;
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

    private void enemiesGetHit() {
        for (Arm arm : this.attacker.enemies) {
            int pos_x = arm.central()[0];
            int pos_y = arm.central()[1];
            int c_x = x + Width / 2;
            int c_y = y + Height / 2;

            int distance = (pos_x-c_x)*(pos_x-c_x) + (pos_y-c_y)*(pos_y-c_y);
            if (distance <= 75*75) {
                if (distance <= 35*35) {
                    arm.hp -= Attack.calculateDamage(attacker, arm) + 20;
                    arm.armor -= 2;
                } else {
                    arm.hp -= Attack.calculateDamage(attacker, arm);
                    arm.armor -= 1;
                }
                if (arm.hp < 0) {
                    arm.alive = false;
                }
            }
        }
    }
}
