package com.alex.terrian;

import com.alex.tank.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IronWall {
    static int IronWall_WIDTH;
    static int IronWall_HEIGHT;
    private final BufferedImage ironWall = ResourceMgr.ironWall;
    int x, y;

    public IronWall(int x, int y) {
        this.x = x;
        this.y = y;
        IronWall_WIDTH = ironWall.getWidth();
        IronWall_HEIGHT = ironWall.getHeight();
    }

    public void paint(Graphics g) {
        g.drawImage(ironWall, x, y, null);
    }
}
