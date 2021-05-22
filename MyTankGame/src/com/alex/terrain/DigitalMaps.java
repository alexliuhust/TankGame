package com.alex.terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitalMaps {
    // 0: Blank
    // 1: Iron wall
    // 2: River
    // 3: Grass
    // 4: Brick wall
    // 5: Ammo
    // 6: Reactive armor
    // 7: Health package

    private static final int[][] broadRiver = {
            {0,0,4,0,0,1,6,0,2,2,2,2,2,2,2,2,0,0,0,0,1,0,0,0},
            {0,0,4,0,0,1,1,0,2,2,2,2,2,2,2,2,0,0,0,0,1,0,5,5},
            {4,0,4,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,1,1,4,1},
            {0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0},
            {4,4,4,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,1,4,1},
            {7,0,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0,7},
            {7,0,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0,7},
            {1,4,1,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,4,4,4},
            {0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0},
            {1,4,1,1,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,4,0,4},
            {5,5,0,1,0,0,0,0,2,2,2,2,2,2,2,2,0,1,1,0,0,4,0,0},
            {0,0,0,1,0,0,0,0,2,2,2,2,2,2,2,2,0,6,1,0,0,4,0,0},
    };

    private static final int[][] grassFort = {
            {0,0,0,0,3,4,3,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,0,0,3,7,3,4,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,3,4,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,3,6,3,4,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {3,3,3,3,3,4,3,3,3,2,2,2,4,2,2,0,0,0,0,0,0,0,0,0},
            {4,7,4,6,4,5,3,4,2,2,7,0,0,7,2,2,2,3,2,3,2,3,2,2},
            {3,3,3,3,3,3,3,3,3,3,0,5,5,0,2,2,4,3,4,3,4,3,4,3},
            {3,4,3,4,3,4,3,4,2,2,0,5,5,0,3,3,3,3,3,3,3,3,3,3},
            {2,2,3,2,3,2,3,2,2,2,7,0,0,7,2,2,4,3,5,4,6,4,7,4},
            {0,0,0,0,0,0,0,0,0,2,2,4,2,2,2,3,3,3,4,3,3,3,3,3},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,4,3,6,3,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,4,3,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,4,3,7,3,0,0,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,3,3,4,3,0,0,0,0},
    };

    public static Map<String, int[][]> map = new HashMap<>(){{
        put("Broad River", broadRiver);
        put("Grass Fort", grassFort);
    }};

    public static int[][] getMap(String name) {
        return map.get(name);
    }

    public static List<String> getAllMaps() {
        List<String> list = new ArrayList<>(map.keySet());
        return list;
    }

}








//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
