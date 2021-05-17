package com.alex.terrain;

import com.alex.drawing.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IronWall {
    public static int IronWall_WIDTH;
    public static int IronWall_HEIGHT;
    private final BufferedImage ironWall = ResourceMgr.ironWall;
    public int x, y;

    public IronWall(int x, int y) {
        this.x = x;
        this.y = y;
        IronWall_WIDTH = ironWall.getWidth();
        IronWall_HEIGHT = ironWall.getHeight();
    }

    public void paint(Graphics g) {
//        System.out.println("iron : " + x + "," + y);
        g.drawImage(ironWall, x, y, null);
    }
}
