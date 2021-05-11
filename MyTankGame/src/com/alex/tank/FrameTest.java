package com.alex.tank;

import java.awt.*;

public class FrameTest {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(100);
            tf.repaint();
        }

    }
}
