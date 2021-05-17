package com.alex.terrain;

import com.alex.tank.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class River {
    public static int River_WIDTH;
    public static int River_HEIGHT;
    private final BufferedImage river = ResourceMgr.river;
    public int x, y;

    public River(int x, int y) {
        this.x = x;
        this.y = y;
        River_WIDTH = river.getWidth();
        River_HEIGHT = river.getHeight();
    }

    public void paint(Graphics g) {
//        System.out.println("river : " + x + "," + y);
        g.drawImage(river, x, y, null);
    }
}
