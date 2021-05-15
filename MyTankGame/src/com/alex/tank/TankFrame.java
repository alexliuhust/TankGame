package com.alex.tank;

import com.alex.terrian.DigitalMaps;
import com.alex.terrian.IronWall;
import com.alex.terrian.Mapper;
import com.alex.terrian.River;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1200, GAME_HEIGHT = 850;

    Tank tank1 = new Tank(0, 350, Dir.RIGHT, this, 1);
    Tank tank2 = new Tank(1150, 350, Dir.LEFT, this, 2);
    public List<Bullet> bullets = new ArrayList<>();
    public List<IronWall> ironWalls = new ArrayList<>();
    public List<River> rivers = new ArrayList<>();

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
        // Terrian draw
        Mapper.drawTerrian(g, DigitalMaps.map1, this);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        paintTankInfo(g, tank2);
        paintTankInfo(g, tank1);
        g.fillRect(0,140, GAME_WIDTH, 10);
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

        // Detect collisions between bullets and tanks
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.fromTank.player == 2) {
                if(b.collideWith(tank1)) break;
            }
            if (b.collideWith(tank2)) break;
        }

        // Detect collision between bullets and walls
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            for (IronWall ironWall : ironWalls) {
                b.collideWithWalls(ironWall);
            }
        }

    }

    private void paintTankInfo(Graphics g, Tank tank) {
        int x = 10;
        if (tank.player == 2) {
            x += 1050;
        }
        if (tank.player == 1) {
            g.drawString("Player 1: " + tank1.hp, x, 42);
        } else {
            g.drawString("Player 2: " + tank2.hp, x, 42);
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
        private boolean L1 = false;
        private boolean R1 = false;
        private boolean U1 = false;
        private boolean D1 = false;

        private boolean L2 = false;
        private boolean R2 = false;
        private boolean U2 = false;
        private boolean D2 = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                // Tanks move
                case KeyEvent.VK_A:
                    L1 = true;
                    break;
                case KeyEvent.VK_D:
                    R1 = true;
                    break;
                case KeyEvent.VK_W:
                    U1 = true;
                    break;
                case KeyEvent.VK_S:
                    D1 = true;
                    break;
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

                // Tanks fire or shift bullets
                case KeyEvent.VK_BACK_QUOTE:
                    tank1.shiftBulletType("left");
                    break;
                case KeyEvent.VK_1:
                    tank1.fire();
                    break;
                case KeyEvent.VK_2:
                    tank1.shiftBulletType("right");
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

            setMainTankDir(L1, R1, U1, D1, tank1);
            setMainTankDir(L2, R2, U2, D2, tank2);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_A:
                    L1 = false;
                    break;
                case KeyEvent.VK_D:
                    R1 = false;
                    break;
                case KeyEvent.VK_W:
                    U1 = false;
                    break;
                case KeyEvent.VK_S:
                    D1 = false;
                    break;
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

            setMainTankDir(L1, R1, U1, D1, tank1);
            setMainTankDir(L2, R2, U2, D2, tank2);
        }

        private void setMainTankDir(boolean L, boolean R, boolean U, boolean D, Tank tank) {
            if (!L && !R && !U && !D) {
                tank.setMoving(false);
                return;
            }
            tank.setMoving(true);
            if (L) tank.setDir(Dir.LEFT);
            if (R) tank.setDir(Dir.RIGHT);
            if (U) tank.setDir(Dir.UP);
            if (D) tank.setDir(Dir.DOWN);
        }
    }
}
