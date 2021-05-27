package frame;

import model.Arm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class BattleField extends Frame {

    public static final int GAME_WIDTH = 580, GAME_HEIGHT = 650;
    public List<Arm> blue_arms = new ArrayList<>();
    public List<Arm> red_arms = new ArrayList<>();
    public boolean[][] board = new boolean[8][8];

    public BattleField() {
        blue_arms.add(new Arm(0,0, Color.BLUE, this));
//        blue_arms.add(new Arm(4,0, Color.BLUE, this));
//        blue_arms.add(new Arm(7,2, Color.BLUE, this));
//        red_arms.add(new Arm(1,1, Color.RED, this));
        red_arms.add(new Arm(7,0, Color.RED, this));
//        red_arms.add(new Arm(5,7, Color.RED, this));

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

        // Set board info
        for (boolean[] row : board) Arrays.fill(row, false);
        for (int i = 0; i < blue_arms.size(); i++) {
            board[blue_arms.get(i).y][blue_arms.get(i).x] = true;
        }
        for (int i = 0; i < red_arms.size(); i++) {
            board[red_arms.get(i).y][red_arms.get(i).x] = true;
        }
        // ==================================
//        for (boolean[] row : board) {
//            for (boolean b : row) {
//                System.out.print(b ? 1 : 0);
//            }
//            System.out.println();
//        }
        // ==================================

        // Drawing arms
        for (int i = 0; i < blue_arms.size(); i++) {
            blue_arms.get(i).paint(g);
        }
        for (int i = 0; i < red_arms.size(); i++) {
            red_arms.get(i).paint(g);
        }
    }

}
