package com.alex.terrain;

import com.alex.tank.ResourceMgr;
import com.alex.tank.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrickWall {
    public static int BrickWall_WIDTH;
    public static int BrickWall_HEIGHT;
    private final BufferedImage brickWall = ResourceMgr.brickWall;
    public int x, y;

    public int hp = 2;
    public boolean live = true;

    public BrickWall(int x, int y) {
        this.x = x;
        this.y = y;
        BrickWall_WIDTH = brickWall.getWidth();
        BrickWall_HEIGHT = brickWall.getHeight();
    }

    public void paint(Graphics g, TankFrame tf) {
        if (!live) {
            tf.brickWalls.remove(this);
            return;
        }
//        System.out.println("brick : " + x + "," + y);
        g.drawImage(brickWall, x, y, null);
    }

    public void getHit() {
        hp -= 1;
        if (hp <= 0) {
            this.live = false;
        }
    }
}
