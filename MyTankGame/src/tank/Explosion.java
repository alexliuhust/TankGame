package tank;

import resource.ResourceMgr;
import frame.BattleFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {

    private String type;
    private int lastingTime = 6;
    public int x, y;
    private BufferedImage exp;
    private BattleFrame tf = null;

    public Explosion(int x, int y, String type, BattleFrame tf) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.tf = tf;

        if (type.equals("AP")) {
            this.exp = ResourceMgr.ap_exp;
        } else if (type.equals("AT")) {
            this.exp = ResourceMgr.at_exp;
        } else {
            this.exp = ResourceMgr.he_exp;
        }
    }

    public void paint(Graphics g) {
        if (lastingTime <= 0) {
            this.tf.explosions.remove(this);
            return;
        }

        this.lastingTime--;
        g.drawImage(this.exp, x, y, null);
    }
}
