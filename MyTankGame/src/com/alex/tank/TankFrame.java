package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank tank2 = new Tank(603, 402, Dir.DOWN, this);
    Tank tank1 = new Tank(203, 402, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();

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
        paintTankInfo(g, tank2);
        paintTankInfo(g, tank1);
        g.setColor(c);

        if (tank1.live && tank2.live) {
            tank1.paint(g, tank2);
            tank2.paint(g, tank1);
        } else if (tank1.live) {
            g.setColor(Color.WHITE);
            g.drawString("Player 1 is the winner!",300, 300);
            g.setColor(c);
            return;
        } else if (tank2.live) {
            g.setColor(Color.WHITE);
            g.drawString("Player 2 is the winner!",300, 300);
            g.setColor(c);
            return;
        } else {
            return;
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // Detect collision
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.fromTank.equals(tank2)) {
                b.collideWith(tank1);
            } else {
                b.collideWith(tank2);
            }
        }

    }

    private void paintTankInfo(Graphics g, Tank tank) {
        int x = 10;
        if (tank.equals(tank2)) {
            x += 600;
        }

        g.drawString("Reload: ", x, 60);
        g.fillRect(x + 50, 50, tank.fireTimeCount, 10);
        g.drawString("AP: " + tank.AP_left, x + 20, 80);
        g.drawString("AT: " + tank.AT_left, x + 20, 100);
        g.drawString("HE: " + tank.HE_left, x + 20, 120);

        if (tank.currentUse == 0) {
            g.fillOval(x, 70, 10, 10);
        } else if (tank.currentUse == 1) {
            g.fillOval(x, 90, 10, 10);
        } else {
            g.fillOval(x, 110, 10, 10);
        }
    }

    class MyKeyListener extends KeyAdapter {
        private boolean L2 = false;
        private boolean R2 = false;
        private boolean U2 = false;
        private boolean D2 = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    L2 = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    R2 = true;
                    break;
                case KeyEvent.VK_UP:
                    U2 = true;
                    break;
                case KeyEvent.VK_DOWN:
                    D2 = true;
                    break;

                case KeyEvent.VK_COMMA:
                    tank2.shiftBulletType("left");
                    break;
                case KeyEvent.VK_PERIOD:
                    tank2.fire();
                    break;
                case KeyEvent.VK_SLASH:
                    tank2.shiftBulletType("right");
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
                    L2 = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    R2 = false;
                    break;
                case KeyEvent.VK_UP:
                    U2 = false;
                    break;
                case KeyEvent.VK_DOWN:
                    D2 = false;
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!L2 && !R2 && !U2 && !D2) {
                tank2.setMoving(false);
                return;
            }
            tank2.setMoving(true);

            if (L2) tank2.setDir(Dir.LEFT);
            if (R2) tank2.setDir(Dir.RIGHT);
            if (U2) tank2.setDir(Dir.UP);
            if (D2) tank2.setDir(Dir.DOWN);
        }
    }
}
