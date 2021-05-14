package com.alex.tank;

public class CalculateDamage {

    public static int bulletDamage(Bullet b) {
        if (b.type.equals("AP")) {
            return 100;
        }

        if (b.type.equals("AT")) {
            return 200;
        }

        if (b.type.equals("HE")) {
            return 300;
        }

        return 0;
    }
}
