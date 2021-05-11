package com.alex.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private Dir dir;
    private static final int SPEED = 5;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();

        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

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
}
