package com.alex.tank;

import java.awt.*;

public class Bullet {

    private TankFrame tf = null;

    private int x;
    private int y;
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    private Dir dir;
    private static final int SPEED = 10;
    private boolean live = true;


    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
            return;
        }

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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
        }
    }

    public void collideWidth(Tank tank) {
        Rectangle rect1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.x, tank.y, Tank.T_WIDTH, Tank.T_HEIGHT);

        if (rect1.intersects(rect2)) {
            this.live = false;
            tank.die();
        }
    }
}
