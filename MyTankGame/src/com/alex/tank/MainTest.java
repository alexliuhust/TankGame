package com.alex.tank;

import java.awt.*;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
//        for (int i = 0; i < 5; i++) {
//            tf.tanks.add(new Tank(600, 100 + i * 60, Dir.RIGHT, tf));
//        }

        while (true) {
            Thread.sleep(35);
            tf.repaint();
        }

    }
}
