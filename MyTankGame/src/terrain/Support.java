package terrain;

import resource.ResourceMgr;
import frame.BattleFrame;
import tank.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Support {
    public static int Support_WIDTH;
    public static int Support_HEIGHT;
    private BufferedImage support;
    public int x, y;
    // 5: Ammo
    // 6: Reactive armor
    // 7: Health package
    int type;

    private int max_disappearing = 250;
    private int disappearing = max_disappearing;

    public Support(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        if (type == 5) {
            this.support = ResourceMgr.ammo;
        } else if (type == 6) {
            this.support = ResourceMgr.rearm;
        } else {
            this.support = ResourceMgr.hpack;
        }
        Support_WIDTH = support.getWidth();
        Support_HEIGHT = support.getHeight();
    }

    public void paint(Graphics g, BattleFrame tf) {
        if (disappearing < max_disappearing) {
            disappearing++;
            return;
        }
        g.drawImage(support, x, y, null);
    }

    public void getPickedUp(Tank tank) {
        if (disappearing < max_disappearing) {
            return;
        }
        disappearing = 0;

        if (type == 5) {
            if (tank.AP_left + tank.AT_left + tank.HE_left > 50) {
                return;
            }
            Random rand = new Random();
            int random_number = rand.nextInt(3);
            if (random_number == 0) {
                tank.AP_left += 5;
            } else if (random_number == 1) {
                tank.AT_left += 5;
            } else {
                tank.HE_left += 5;
            }
        }
        else if (type == 6) {
            if (tank.reactiveArmor < tank.max_reactiveArmor) {
                tank.reactiveArmor++;
                tank.reactiveArmor = Math.min(tank.reactiveArmor, tank.max_reactiveArmor);
            }
        }
        else {
            if (tank.hp < tank.max_hp) {
                tank.hp += 200;
                tank.hp = Math.min(tank.max_hp, tank.hp);
            }
        }
    }

}
