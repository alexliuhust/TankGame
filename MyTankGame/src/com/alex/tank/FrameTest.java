package com.alex.tank;

import java.awt.*;

public class FrameTest {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
