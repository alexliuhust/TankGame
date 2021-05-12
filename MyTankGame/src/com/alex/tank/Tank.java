package com.alex.tank;

import java.awt.*;

public class Tank {

    private TankFrame tf = null;

    int x = 100;
    int y = 100;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    static final int T_WIDTH = ResourceMgr.tankL.getWidth(), T_HEIGHT = ResourceMgr.tankL.getHeight();
    private boolean moving = false;
    private boolean live = true;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.tanks.remove(this);
            return;
        }

        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;

            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                if (x > 0) {
                    x -= SPEED;
                }
                break;
            case RIGHT:
                if (x < TankFrame.GAME_WIDTH - T_WIDTH) {
                    x += SPEED;
                }
                break;
            case UP:
                if (y > 30) {
                    y -= SPEED;
                }
                break;
            case DOWN:
                if (y < TankFrame.GAME_HEIGHT - T_HEIGHT) {
                    y += SPEED;
                }
                break;

            default:
                break;
        }
    }

    public void die() {
        this.live = false;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int bx = x + T_WIDTH / 2 - Bullet.WIDTH / 2;
        int by = y + T_HEIGHT / 2 - Bullet.HEIGHT / 2;

        tf.bullets.add(new Bullet(bx, by, this.dir, this.tf));
    }
}
