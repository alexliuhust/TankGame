package com.alex.tank;

import com.alex.drawing.TankFrame;
import com.alex.terrain.BrickWall;
import com.alex.terrain.IronWall;

import java.awt.*;

public class Bullet {

    private TankFrame tf = null;
    Tank fromTank;

    private int x;
    private int y;
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    private Dir dir;
    private static final int SPEED = 35;
    private boolean live = true;
    int flyingTime = 0;

    public String type;


    public Bullet(int x, int y, Dir dir, TankFrame tf, String type, Tank fromTank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.type = type;
        this.fromTank = fromTank;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
            return;
        }

        if (collideWithTanks(tf.tank1) || collideWithTanks(tf.tank2)) {
            return;
        }

        for (IronWall ironWall : tf.ironWalls) {
            if (collideWithIronWalls(ironWall)) {
                return;
            }
        }

        for (BrickWall brick : tf.brickWalls) {
            if (collideWithBrickWalls(brick)) {
                return;
            }
        }


        this.flyingTime++;

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();
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

        if (x < -SPEED || y < 150-SPEED || x > TankFrame.GAME_WIDTH + SPEED || y > TankFrame.GAME_HEIGHT + SPEED) {
            this.live = false;
        }
    }

    public boolean collideWithTanks(Tank tank) {
        boolean ans = false;
        Rectangle rect1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.x, tank.y, Tank.T_WIDTH, Tank.T_HEIGHT);

        if (rect1.intersects(rect2)) {
            this.live = false;
            tank.getHit(this);
            ans = true;
        }
        return ans;
    }

    public boolean collideWithIronWalls(IronWall ironWall) {
        Rectangle rect1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(ironWall.x, ironWall.y, IronWall.IronWall_WIDTH, IronWall.IronWall_HEIGHT);
        if (rect1.intersects(rect2)) {
            this.live = false;
            return true;
        }
        return false;
    }

    public boolean collideWithBrickWalls(BrickWall brickWall) {
        Rectangle rect1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(brickWall.x, brickWall.y, BrickWall.BrickWall_WIDTH, BrickWall.BrickWall_HEIGHT);
        if (rect1.intersects(rect2)) {
            this.live = false;
            brickWall.getHit(this);
            return true;
        }
        return false;
    }
}
