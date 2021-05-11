package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN);

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
        myTank.paint(g);
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
            myTank.setMoving(true);
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
            myTank.setMoving(false);
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (L) myTank.setDir(Dir.LEFT);
            if (R) myTank.setDir(Dir.RIGHT);
            if (U) myTank.setDir(Dir.UP);
            if (D) myTank.setDir(Dir.DOWN);
        }
    }
}

















