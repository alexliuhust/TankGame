package com.alex.drawing;

import com.alex.tank.Bullet;
import com.alex.tank.Dir;
import com.alex.tank.Tank;
import com.alex.terrain.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1200, GAME_HEIGHT = 850;

    public Tank tank1;
    public Tank tank2;
    public String map_name;
    public List<Bullet> bullets = new ArrayList<>();

    public List<IronWall> ironWalls = new ArrayList<>();
    public List<River> rivers = new ArrayList<>();
    public List<Grass> grasses = new ArrayList<>();
    public List<BrickWall> brickWalls = new ArrayList<>();
    public List<Support> supports = new ArrayList<>();

    public TankFrame(String tank1type, String tank2type, String mapName) {
        tank1 = new Tank(10,   160, Dir.RIGHT, this, 1, tank1type);
        tank2 = new Tank(1150, 800, Dir.LEFT,  this, 2, tank2type);
        Mapper.addTerrain(DigitalMaps.getMap(mapName), this);
        this.map_name = mapName;

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
        this.bullets.add(new Bullet(0, 0, Dir.UP, this, "AT", null));

        // Map Name
        paintMapName(g);
        // Terrain drawing
        paintTerrain(g);
        // Show info of tanks
        paintInfoBar(g);
        // Tanks drawing
        if (!paintTanks(g)) return;
        // Bullets drawing
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
    }

    private void paintMapName(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        Font originalFont = g.getFont();
        Font titleFont = originalFont.deriveFont(originalFont.getSize() * 5.0F);
        g.setFont(titleFont);
        g.drawString(map_name, 450, 100);
        g.setFont(originalFont);
        g.setColor(c);
    }

    private void paintTerrain(Graphics g) {
        for (IronWall iron : this.ironWalls) iron.paint(g);
        for (River river : this.rivers) river.paint(g);
        for (Grass grass : this.grasses) grass.paint(g);
        for (int i = brickWalls.size() - 1; i >= 0; i--) {
            BrickWall brick = brickWalls.get(i);
            brick.paint(g, this);
        }
        for (int i = supports.size() - 1; i >= 0; i--) {
            Support support = supports.get(i);
            support.paint(g, this);
        }
    }

    private void paintInfoBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        paintTankInfo(g, tank2);
        paintTankInfo(g, tank1);
        g.fillRect(0,140, GAME_WIDTH, 10);
        g.setColor(c);
    }

    private void paintTankInfo(Graphics g, Tank tank) {
        BufferedImage tankIcon = ResourceMgr.tankU1;
        int x = 70;
        if (tank.player == 2) {
            x += 1000;
            tankIcon = ResourceMgr.tankU2;
        }
        g.drawImage(tankIcon,x - 55, 35, null);

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F);
        g.setFont(newFont);

        g.drawString(tank.type, x - 55, 90);
        if (tank.player == 1) {
            g.drawString("Player 1: " + tank1.hp + "[" + tank1.reactiveArmor + "]", x, 45);
        } else {
            g.drawString("Player 2: " + tank2.hp + "[" + tank2.reactiveArmor + "]", x, 45);
        }
        g.drawString("Reload: ", x, 70);
        g.fillRect(x + 50, 60, tank.fireTimeCount, 10);
        g.drawString("AP: " + tank.AP_left, x + 20, 90);
        g.drawString("AT: " + tank.AT_left, x + 20, 110);
        g.drawString("HE: " + tank.HE_left, x + 20, 130);

        g.setFont(currentFont);

        if (tank.currentUse == 0) {
            g.fillOval(x, 80, 10, 10);
        } else if (tank.currentUse == 1) {
            g.fillOval(x, 100, 10, 10);
        } else {
            g.fillOval(x, 120, 10, 10);
        }
    }

    private boolean paintTanks(Graphics g) {
        Color c = g.getColor();
        if (tank1.live && tank2.live) {
            tank1.paint(g, tank2);
            tank2.paint(g, tank1);
            return true;
        } else if (tank1.live) {
            g.setColor(Color.WHITE);
            g.drawString("Player 1 is the winner!",tank1.x, tank1.y);
            g.setColor(c);
            return false;
        } else if (tank2.live) {
            g.setColor(Color.WHITE);
            g.drawString("Player 2 is the winner!",tank2.x, tank2.y);
            g.setColor(c);
            return false;
        } else {
            return false;
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
