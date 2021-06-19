package tank;

import common.Collision;
import resource.ResourceMgr;
import frame.BattleFrame;
import terrain.BrickWall;
import terrain.IronWall;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    public BattleFrame tf = null;
    public Tank fromTank;

    public int x;
    public int y;
    public static int WIDTH;
    public static int HEIGHT;
    public Dir dir;
    private static final int SPEED = 35;
    public boolean live = true;
    public int flyingTime = 0;

    public String type;
    private BufferedImage bulletL, bulletR, bulletU, bulletD;


    public Bullet(int x, int y, Dir dir, BattleFrame tf, String type, Tank fromTank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.type = type;
        this.fromTank = fromTank;
        loadTypeImages();

        WIDTH = bulletD.getWidth();
        HEIGHT = bulletD.getHeight();
    }

    private void loadTypeImages() {
        if (this.type.equals("AP")) {
            this.bulletL = ResourceMgr.apL;
            this.bulletR = ResourceMgr.apR;
            this.bulletU = ResourceMgr.apU;
            this.bulletD = ResourceMgr.apD;
        }
        else if (this.type.equals("AT")) {
            this.bulletL = ResourceMgr.atL;
            this.bulletR = ResourceMgr.atR;
            this.bulletU = ResourceMgr.atU;
            this.bulletD = ResourceMgr.atD;
        }
        else {
            this.bulletL = ResourceMgr.heL;
            this.bulletR = ResourceMgr.heR;
            this.bulletU = ResourceMgr.heU;
            this.bulletD = ResourceMgr.heD;
        }
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
            return;
        }

        Collision.detectCollisionsForBullet(this);

        this.flyingTime++;

        loadDirImages(g);
        move();
    }

    public void bringExplosionImage() {
        tf.explosions.add(new Explosion(x - 9, y - 9, this.type, tf));
    }

    private void loadDirImages(Graphics g) {
        switch(dir) {
            case LEFT:
                g.drawImage(bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(bulletD, x, y, null);
                break;

            default:
                break;
        }
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;

            default:
                break;
        }

        if (x < -SPEED || y < 150-SPEED || x > BattleFrame.GAME_WIDTH + SPEED || y > BattleFrame.GAME_HEIGHT + SPEED) {
            this.live = false;
        }
    }

}
