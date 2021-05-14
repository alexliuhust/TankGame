package com.alex.tank;

import java.awt.*;
import java.util.Arrays;

public class Tank {

    private TankFrame tf = null;

    int x = 100;
    int y = 100;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    static final int T_WIDTH = ResourceMgr.tankL.getWidth();
    static final int T_HEIGHT = ResourceMgr.tankL.getHeight();
    private final boolean[] isBlock = new boolean[4];

    private boolean moving = false;
    private boolean live = true;
    int fireTimeCount = 30;
    int fullFireTime = 30;
    int max_hp = 1000;
    int hp = 1000;

    int AP_left = 10;
    int AT_left = 10;
    int HE_left = 10;
    int currentUse = 0;


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
        if (fireTimeCount != fullFireTime) {
            fireTimeCount++;
        }

        drawHpBar(g);
    }

    private void drawHpBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, T_WIDTH, 3);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, T_WIDTH * (hp * 100 / max_hp) / 100, 3);

        g.setColor(c);
    }

    private void move() {
        if (!moving) return;

        // Detect collisions between myTank and other tanks
        for (Tank tank : tf.tanks) {
            // Left:
            if (x <= tank.x + T_WIDTH && x >= tank.x + T_WIDTH - SPEED
                    && y <= tank.y + T_HEIGHT && y >= tank.y - T_HEIGHT)
                isBlock[0] = true;
            // Right:
            if (x >= tank.x - T_WIDTH && x <= tank.x - T_WIDTH + SPEED
                    && y <= tank.y + T_HEIGHT && y >= tank.y - T_HEIGHT)
                isBlock[1] = true;
            // Up:
            if (y <= tank.y + T_HEIGHT && y >= tank.y + T_HEIGHT - SPEED
                    && x <= tank.x + T_WIDTH && x >= tank.x - T_WIDTH)
                isBlock[2] = true;
            // Down:
            if (y >= tank.y - T_HEIGHT && y <= tank.y - T_HEIGHT + SPEED
                    && x <= tank.x + T_WIDTH && x >= tank.x - T_WIDTH)
                isBlock[3] = true;
        }

        switch (dir) {
            case LEFT:
                if (x > 0 && !isBlock[0]) {
                    x -= SPEED;
                }
                break;
            case RIGHT:
                if (x < TankFrame.GAME_WIDTH - T_WIDTH && !isBlock[1]) {
                    x += SPEED;
                }
                break;
            case UP:
                if (y > 30 && !isBlock[2]) {
                    y -= SPEED;
                }
                break;
            case DOWN:
                if (y < TankFrame.GAME_HEIGHT - T_HEIGHT && !isBlock[3]) {
                    y += SPEED;
                }
                break;

            default:
                break;
        }

        Arrays.fill(isBlock, false);
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
        if (fireTimeCount != fullFireTime) {
            return;
        }

        if (currentUse == 0 && this.AP_left == 0
                || currentUse == 1 && this.AT_left == 0
                || currentUse == 2 && this.HE_left == 0) {
            return;
        }

        String fire_type;
        if (currentUse == 0) {
            fire_type = "AP";
            AP_left--;
        } else if (currentUse == 1) {
            fire_type = "AT";
            AT_left--;
        } else {
            fire_type = "HE";
            HE_left--;
        }

        int bx = x + T_WIDTH / 2 - Bullet.WIDTH / 2;
        int by = y + T_HEIGHT / 2 - Bullet.HEIGHT / 2;

        tf.bullets.add(new Bullet(bx, by, this.dir, this.tf, fire_type));
        fireTimeCount = 0;
    }

    public void getHit(Bullet b) {
        int damage = b.damage;
        this.hp -= damage;
        if (hp <= 0) {
            this.live = false;
        }
    }

    public void shiftBulletType(String leftOrRight) {
        this.fireTimeCount = 0;
        if (leftOrRight.equals("left")) {
            this.currentUse--;
        } else if (leftOrRight.equals("right")) {
            this.currentUse++;
        }

        if (currentUse == 3) {
            this.currentUse = 0;
        } else if (currentUse == -1) {
            this.currentUse = 2;
        }
    }
}
