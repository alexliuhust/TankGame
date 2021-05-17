package com.alex.terrain;

import com.alex.tank.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrickWall {
    public static int BrickWall_WIDTH;
    public static int BrickWall_HEIGHT;
    private final BufferedImage brickWall = ResourceMgr.brickWall;
    public int x, y;

    public BrickWall(int x, int y) {
        this.x = x;
        this.y = y;
        BrickWall_WIDTH = brickWall.getWidth();
        BrickWall_HEIGHT = brickWall.getHeight();
    }

    public void paint(Graphics g) {
        g.drawImage(brickWall, x, y, null);
    }
}
