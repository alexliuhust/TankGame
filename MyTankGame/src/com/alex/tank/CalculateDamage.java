package com.alex.tank;

import java.util.Random;

public class CalculateDamage {

    public static int bulletDamage(Bullet b) {
        Random rand = new Random();
        int random_number = rand.nextInt(100);

        if (b.type.equals("AP")) {
            int penetrateProb = 100 - b.flyingTime * 3;
            if (random_number <= penetrateProb) {
                return 500;
            } else {
                return 10;
            }
        }

        if (b.type.equals("AT")) {
            int penetrateProb = 60;
            if (random_number <= penetrateProb) {
                return 400;
            } else {
                return 200;
            }
        }

        if (b.type.equals("HE")) {
            int penetrateProb = 10 - b.flyingTime;
            if (random_number <= penetrateProb) {
                return 800;
            } else {
                return 200;
            }
        }

        return 0;
    }
}
