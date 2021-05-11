package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 100;
    int y = 100;

    Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

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

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;

            default:
                break;
        }


    }

    class MyKeyListener extends KeyAdapter {
        private boolean L = false;
        private boolean R = false;
        private boolean U = false;
        private boolean D = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    L = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    R = true;
                    break;
                case KeyEvent.VK_UP:
                    U = true;
                    break;
                case KeyEvent.VK_DOWN:
                    D = true;
                    break;

                default:
                    break;
            }

//            if (L) x -= 10;
//            if (R) x += 10;
//            if (U) y -= 10;
//            if (D) y += 10;

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    L = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    R = false;
                    break;
                case KeyEvent.VK_UP:
                    U = false;
                    break;
                case KeyEvent.VK_DOWN:
                    D = false;
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (L) dir = Dir.LEFT;
            if (R) dir = Dir.RIGHT;
            if (U) dir = Dir.UP;
            if (D) dir = Dir.DOWN;
        }
    }
}

















