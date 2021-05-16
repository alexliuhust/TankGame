package com.alex.terrain;

import com.alex.tank.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass {
    public static int Grass_WIDTH;
    public static int Grass_HEIGHT;
    private final BufferedImage grass = ResourceMgr.grass;
    public int x, y;

    public Grass(int x, int y) {
        this.x = x;
        this.y = y;
        Grass_WIDTH = grass.getWidth();
        Grass_HEIGHT = grass.getHeight();
    }

    public void paint(Graphics g) {
        g.drawImage(grass, x, y, null);
    }
}
