package com.alex.terrain;

public class DigitalMaps {
    // 0: Blank
    // 1: Iron wall
    // 2: River
    // 3: Grass
    // 4: Brick wall
    // 5: Ammo
    // 6: Reactive armor
    // 7: Health package

    public static final int[][] map1 = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,0,0,0,4,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,2,0,0,4,0,0,2,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,2,0,0,4,0,0,2,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,2,0,0,4,0,0,2,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,2,0,0,4,0,0,2,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,0,0,0,4,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,1,0,3,0,0,0,0,0,0,4,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public static final int[][] map2 = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0,5,0,0,0,0,0,0,0,0,5,0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,2,0,0,0,6,0,0,0,0,0,0,0,0,6,0,0,0,2,0,0,0},
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0},
            {0,0,0,3,0,0,0,7,0,0,0,0,0,0,0,0,7,0,0,0,3,0,0,0},
            {0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0},
            {0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0},
            {0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
}
