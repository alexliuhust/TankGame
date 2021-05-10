package com.alex.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame {
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(800, 600);
        f.setResizable(false);
        f.setTitle("Tank Game");
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
