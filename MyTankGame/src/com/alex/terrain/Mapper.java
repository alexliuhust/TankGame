package com.alex.terrain;

import com.alex.tank.TankFrame;

import java.awt.*;

public class Mapper {

    public static void drawTerrain(Graphics g, int[][] map, TankFrame tf) {
        int step = River.River_HEIGHT;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int val = map[i][j];
                if (val == 1) {
                    IronWall iron = new IronWall(j * step, i * step + 150);
                    tf.ironWalls.add(iron);
                    iron.paint(g);
                } else if (val == 2) {
                    River river = new River(j * step, i * step + 150);
                    tf.rivers.add(river);
                    river.paint(g);
                } else if (val == 3) {
                    Grass grass = new Grass(j * step, i * step + 150);
                    tf.grasses.add(grass);
                    grass.paint(g);
                }
            }
        }
    }

}
