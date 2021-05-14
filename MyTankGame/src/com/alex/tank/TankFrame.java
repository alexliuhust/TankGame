package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(403, 402, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        paintMyTankInfo(g);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        // Detect collision between every bullet and every tank
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

    }

    private void paintMyTankInfo(Graphics g) {
        g.drawString("Reload: ", 10, 60);
        g.fillRect(60, 50, myTank.fireTimeCount, 10);
        g.drawString("AP: " + myTank.AP_left, 30, 80);
        g.drawString("AT: " + myTank.AT_left, 30, 100);
        g.drawString("HE: " + myTank.HE_left, 30, 120);

        if (myTank.currentUse == 0) {
            g.fillOval(10, 70, 10, 10);
        } else if (myTank.currentUse == 1) {
            g.fillOval(10, 90, 10, 10);
        } else {
            g.fillOval(10, 110, 10, 10);
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
                case KeyEvent.VK_COMMA:
                    myTank.shiftBulletType("left");
                    break;
                case KeyEvent.VK_PERIOD:
                    myTank.shiftBulletType("right");
                    break;

                default:
                    break;
            }

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
                case KeyEvent.VK_SLASH:
                    myTank.fire();
                    break;


                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!L && !R && !U && !D) {
                myTank.setMoving(false);
                return;
            }
            myTank.setMoving(true);

            if (L) myTank.setDir(Dir.LEFT);
            if (R) myTank.setDir(Dir.RIGHT);
            if (U) myTank.setDir(Dir.UP);
            if (D) myTank.setDir(Dir.DOWN);
        }
    }
}

















