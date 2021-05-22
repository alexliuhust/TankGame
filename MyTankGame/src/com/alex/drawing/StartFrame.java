package com.alex.drawing;

import com.alex.terrain.DigitalMaps;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class StartFrame extends Frame {
    public static final int WIDTH = 630, HEIGHT = 600;
    public List<String> tankTypes = new ArrayList<>();
    public List<String> mapNames;

    public int tank1selectedIndex = 0;
    public int tank2selectedIndex = 0;
    public int mapSelectedIndex = 0;

    public boolean closed = false;

    public StartFrame() {
        this.tankTypes.add("Heavy");
        this.tankTypes.add("Medium");
        this.tankTypes.add("Light");
        this.mapNames = DigitalMaps.getAllMaps();

        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Start Menu");
        this.setVisible(true);
        this.addKeyListener(new StartKeyListener());
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
            offScreenImage = this.createImage(WIDTH, HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color originalColor = g.getColor();
        Font originalFont = g.getFont();
        Font textFont = originalFont.deriveFont(originalFont.getSize() * 2.0F);
        Font titleFont = originalFont.deriveFont(originalFont.getSize() * 2.5F);

        g.setColor(Color.WHITE);
        int x1 = 50, x_m = 245, x2 = 480;
        g.setFont(titleFont);
        g.drawString("PLAY 1", x1, 140);
        g.drawString("MAPS", x_m + 20, 140);
        g.drawString("PLAY 2", x2, 140);


        g.setFont(textFont);
        paintTankTypes(g, x1);
        paintTankTypes(g, x2);
        paintMapNames(g, x_m);
        g.drawRect(x1 - 10, 170 + this.tank1selectedIndex * 50, 100, 40);
        g.drawRect(x2 - 10, 170 + this.tank2selectedIndex * 50, 100, 40);
        g.drawRect(x_m - 10, 170 + this.mapSelectedIndex * 50, 160, 40);

        g.setFont(originalFont);
        g.setColor(originalColor);
    }

    private void paintTankTypes(Graphics g, int start_x) {
        for (int i = 0; i < tankTypes.size(); i++) {
            g.drawString(tankTypes.get(i), start_x, 200 + i * 50);
        }
    }

    private void paintMapNames(Graphics g, int start_x) {
        for (int i = 0; i < mapNames.size(); i++) {
            g.drawString(mapNames.get(i), start_x, 200 + i * 50);
        }
    }

    class StartKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                // Tanks move
                case KeyEvent.VK_W:
                    tank1selectedIndex--;
                    if (tank1selectedIndex == -1) {
                        tank1selectedIndex = tankTypes.size() - 1;
                    }
                    break;
                case KeyEvent.VK_S:
                    tank1selectedIndex++;
                    if (tank1selectedIndex == tankTypes.size()) {
                        tank1selectedIndex = 0;
                    }
                    break;

                case KeyEvent.VK_UP:
                    tank2selectedIndex--;
                    if (tank2selectedIndex == -1) {
                        tank2selectedIndex = tankTypes.size() - 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    tank2selectedIndex++;
                    if (tank2selectedIndex == tankTypes.size()) {
                        tank2selectedIndex = 0;
                    }
                    break;


                case KeyEvent.VK_G:
                    mapSelectedIndex++;
                    if (mapSelectedIndex == mapNames.size()) {
                        mapSelectedIndex = 0;
                    }
                    break;

                case KeyEvent.VK_ENTER:
                    System.out.println("Close the frame");
                    closed = true;
                    break;

                default:
                    break;
            }
        }
    }

}
