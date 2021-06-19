package common;

import tank.Bullet;
import tank.Dir;
import tank.Tank;

import java.util.Random;

public class CalculateDamage {

    public static int bulletDamage(Bullet b, Tank tank) {
        Random rand = new Random();
        int random_number = rand.nextInt(100);

        int armor = getCalculatedArmor(b, tank);

        if (b.type.equals("AP")) {
            int penetrateProb = 100 - b.flyingTime * 3 - armor * 2;
            if (random_number <= penetrateProb) {
                return 500 - armor * 5;
            } else {
                return 10;
            }
        }

        if (b.type.equals("AT")) {
            int penetrateProb = 60 - armor * 2;
            if (random_number <= penetrateProb) {
                return 400 - armor * 2;
            } else {
                return 200 - armor * 2;
            }
        }

        if (b.type.equals("HE")) {
            int penetrateProb = 10 - b.flyingTime - armor * 2;
            if (random_number <= penetrateProb) {
                return 800;
            } else {
                return 250 - armor * 2;
            }
        }

        return 0;
    }

    private static int getCalculatedArmor(Bullet b, Tank tank) {
        if (b.dir == tank.dir) {
            return tank.rearArmor;
        }

        if (b.dir == Dir.LEFT && tank.dir == Dir.RIGHT
        || b.dir == Dir.RIGHT && tank.dir == Dir.LEFT
        || b.dir == Dir.UP && tank.dir == Dir.DOWN
        || b.dir == Dir.DOWN && tank.dir == Dir.UP) {
            return tank.frontArmor;
        }

        return tank.sideArmor;
    }
}
