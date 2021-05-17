package com.alex.tank;

import com.alex.drawing.ResourceMgr;
import com.alex.drawing.TankFrame;
import com.alex.terrain.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Tank {

    private TankFrame tf;
    private BufferedImage tankL, tankR, tankU, tankD;

    public int player;
    public int x;
    public int y;
    public Dir dir;
    int SPEED = 5;
    static final int T_WIDTH = 30;
    static final int T_HEIGHT = 30;
    private final boolean[] isBlock = new boolean[4];
    private boolean inGrass = false;

    private boolean moving = false;
    public boolean live = true;
    public int fullFireTime = 50;
    public int fireTimeCount = fullFireTime;
    public int max_hp = 2000;
    public int hp = max_hp;
    public int frontArmor = 20;
    public int sideArmor = 5;
    public int rearArmor = -20;

    public int AP_left = 10;
    public int AT_left = 10;
    public int HE_left = 10;
    public int currentUse = 0;


    public Tank(int x, int y, Dir dir, TankFrame tf, int player) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.player = player;

        if (player == 1) {
            this.tankL = ResourceMgr.tankL1;
            this.tankR = ResourceMgr.tankR1;
            this.tankU = ResourceMgr.tankU1;
            this.tankD = ResourceMgr.tankD1;
        } else {
            this.tankL = ResourceMgr.tankL2;
            this.tankR = ResourceMgr.tankR2;
            this.tankU = ResourceMgr.tankU2;
            this.tankD = ResourceMgr.tankD2;
        }
    }

    public void paint(Graphics g, Tank enemy) {
        // Load corresponding tank image
        switch(dir) {
            case LEFT:
                g.drawImage(tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(tankR, x, y, null);
                break;
            case UP:
                g.drawImage(tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(tankD, x, y, null);
                break;

            default:
                break;
        }

        move(enemy);

        if (fireTimeCount != fullFireTime) {
            fireTimeCount++;
        }

        drawHpBar(g);
    }

    private void move(Tank tank) {
        if (!moving) return;

        // Detect collisions among tanks or between tanks and terrain
        detectTankOrTerrainCollisions(tank.x, tank.y, T_WIDTH, T_HEIGHT, false);
        for (IronWall iron : this.tf.ironWalls) {
            detectTankOrTerrainCollisions(iron.x, iron.y, IronWall.IronWall_WIDTH, IronWall.IronWall_HEIGHT, false);
        }
        for (River river : this.tf.rivers) {
            detectTankOrTerrainCollisions(river.x, river.y, River.River_WIDTH, River.River_HEIGHT, false);
        }
        for (BrickWall brick : this.tf.brickWalls) {
            detectTankOrTerrainCollisions(brick.x, brick.y, BrickWall.BrickWall_WIDTH, BrickWall.BrickWall_HEIGHT, false);
        }

        this.inGrass = false;
        for (Grass grass : this.tf.grasses) {
            if (detectTankOrTerrainCollisions(grass.x, grass.y, Grass.Grass_WIDTH, Grass.Grass_HEIGHT, true)){
                this.inGrass = true;
                break;
            }
        }
        if (inGrass) this.SPEED = 2;
        else this.SPEED = 5;

        // Move the tank
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
                if (y > 150 && !isBlock[2]) {
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

    private boolean detectTankOrTerrainCollisions(int ox, int oy, int W, int H, boolean isGrass) {
        if (isGrass) {
            Rectangle rect1 = new Rectangle(x, y, T_WIDTH, T_HEIGHT);
            Rectangle rect2 = new Rectangle(ox, oy, W, H);
            return rect1.intersects(rect2);
        }

        else {
            // Left:
            if (x <= ox + W && x >= ox + W - SPEED
                    && y > oy - T_HEIGHT && y < oy + H)
                isBlock[0] = true;
            // Right:
            if (x >= ox - T_WIDTH && x <= ox - T_WIDTH + SPEED
                    && y > oy - T_HEIGHT && y < oy + H)
                isBlock[1] = true;
            // Up:
            if (y <= oy + H && y >= oy + H - SPEED
                    && x > ox - T_WIDTH && x < ox + W)
                isBlock[2] = true;
            // Down:
            if (y >= oy - T_HEIGHT && y <= oy - T_HEIGHT + SPEED
                    && x > ox - T_WIDTH && x < ox + W)
                isBlock[3] = true;
        }
        return false;
    }

    private void drawHpBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, T_WIDTH, 3);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, T_WIDTH * (hp * 100 / max_hp) / 100, 3);

        g.setColor(c);
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
        switch (dir) {
            case LEFT:
                bx -= 25;break;
            case RIGHT:
                bx += 25;break;
            case UP:
                by -= 25;break;
            case DOWN:
                by += 25;break;
        }

        Bullet b = new Bullet(bx, by, this.dir, this.tf, fire_type, this);
        if (!b.collideWithTanks(tf.tank1) && !b.collideWithTanks(tf.tank2)) {
            tf.bullets.add(b);
        }

        fireTimeCount = 0;
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

    public void getHit(Bullet b) {
        int damage = CalculateDamage.bulletDamage(b, this);
        System.out.println(damage);
        this.hp -= damage;
        if (hp <= 0) {
            this.live = false;
        }
    }

}
