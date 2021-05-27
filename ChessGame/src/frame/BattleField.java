package frame;

import model.Arm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BattleField extends Frame {

    public static final int GAME_WIDTH = 580, GAME_HEIGHT = 650;
    public Arm blue1, red1, red2;

    public BattleField() {
        this.blue1 = new Arm(0,0, Color.BLUE);
        this.red1 = new Arm(1,1, Color.RED);
        this.red2 = new Arm(3,6, Color.RED);

        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("My Chess Game");
        this.setVisible(true);

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
        Color originalColor = g.getColor();
        g.setColor(Color.GRAY);
        // Drawing horizontal lines
        for (int i = 0; i < 9; i++) {
            g.fillRect(45,95 + i * 60,490, 10);
        }
        // Drawing vertical lines
        for (int i = 0; i < 9; i++) {
            g.fillRect(45 + i * 60,95,10, 490);
        }
        g.setColor(originalColor);

        this.blue1.paint(g);
        this.red1.paint(g);
        this.red2.paint(g);
    }

}
