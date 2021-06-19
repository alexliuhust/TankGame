package terrain;

import tank.Bullet;
import resource.ResourceMgr;
import frame.BattleFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrickWall {
    public static int BrickWall_WIDTH;
    public static int BrickWall_HEIGHT;
    private final BufferedImage brickWall = ResourceMgr.brickWall;
    private final BufferedImage damagedBrick = ResourceMgr.damagedBrick;
    public int x, y;

    public int hp = 3;
    public boolean live = true;

    public BrickWall(int x, int y) {
        this.x = x;
        this.y = y;
        BrickWall_WIDTH = brickWall.getWidth();
        BrickWall_HEIGHT = brickWall.getHeight();
    }

    public void paint(Graphics g, BattleFrame tf) {
        if (!live) {
            tf.brickWalls.remove(this);
            return;
        }
//        System.out.println("brick : " + x + "," + y);
        if (hp == 3) {
            g.drawImage(brickWall, x, y, null);
        } else {
            g.drawImage(damagedBrick, x, y, null);
        }
    }

    public void getHit(Bullet b) {
        if (b.type.equals("HE")) {
            this.live = false;
            return;
        }

        hp -= 1;
        if (hp <= 0) {
            this.live = false;
        }
    }
}
