package com.alex.tank;

import java.awt.*;

public class Tank {

    private TankFrame tf = null;

    private int x = 100;
    private int y = 100;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private static final int T_WIDTH = 50, T_HEIGHT = 50;
    private boolean moving = false;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();

        g.setColor(Color.YELLOW);
        g.fillRect(x, y, T_WIDTH, T_HEIGHT);
        g.setColor(c);

        if (!moving) return;

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
        tf.bullets.add(new Bullet(x + T_WIDTH / 2 - 5, y + T_HEIGHT / 2 - 5, this.dir, this.tf));
    }
}
