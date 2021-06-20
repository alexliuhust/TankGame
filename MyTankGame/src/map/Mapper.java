package map;

import frame.BattleFrame;
import terrain.*;

public class Mapper {

    public static void addTerrain(int[][] map, BattleFrame tf) {
        int step = 50;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int val = map[i][j];

                if (val == 1) {
                    IronWall iron = new IronWall(j * step, i * step + 150);
                    tf.ironWalls.add(iron);
                }

                else if (val == 2) {
                    River river = new River(j * step, i * step + 150);
                    tf.rivers.add(river);
                }

                else if (val == 3) {
                    Grass grass = new Grass(j * step, i * step + 150);
                    tf.grasses.add(grass);
                }

                else if (val == 4) {
                    Grass grass = new Grass(j * step, i * step + 150);
                    tf.grasses.add(grass);
                    BrickWall brickWall = new BrickWall(j * step, i * step + 150);
                    tf.brickWalls.add(brickWall);
                }

                // =========================================================================
                else if (val == 5 || val == 6 || val == 7) {
                    Grass grass = new Grass(j * step, i * step + 150);
                    tf.grasses.add(grass);
                    Support support = new Support(j * step, i * step + 150, val);
                    tf.supports.add(support);
                }
            }
        }
    }

}
