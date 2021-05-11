package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 100;
    int y = 100;

    public TankFrame() {
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("Tank Game");
        this.setVisible(true);

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    x -= 50;
//                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    x += 50;
//                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    y -= 50;
//                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    y += 50;
//                    repaint();
                    break;

                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("Released");
        }
    }
}

















