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
    public List<Arm> yellow_arms = new ArrayList<>();

    public boolean[][] board = new boolean[8][8];

    public BattleField() {
        red_arms.add(new Arm(0,0, Color.RED, this));
        red_arms.add(new Arm(5,0, Color.RED, this));
        red_arms.add(new Arm(6,0, Color.RED, this));

        blue_arms.add(new Arm(1,7, Color.BLUE, this));
        blue_arms.add(new Arm(2,7, Color.BLUE, this));
        blue_arms.add(new Arm(7,7, Color.BLUE, this));

//        yellow_arms.add(new Arm(3,4, Color.YELLOW, this));
//        yellow_arms.add(new Arm(1,4, Color.YELLOW, this));
//        yellow_arms.add(new Arm(0,4, Color.YELLOW, this));
//        yellow_arms.add(new Arm(2,0, Color.YELLOW, this));
//        yellow_arms.add(new Arm(2,1, Color.YELLOW, this));
//        yellow_arms.add(new Arm(5,6, Color.YELLOW, this));
//        yellow_arms.add(new Arm(6,6, Color.YELLOW, this));

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

        g.setColor(Color.WHITE);
        g.drawString("Red  Player has " + red_arms.size() + " arms", 45, 75);
        g.drawString("Blue Player has " + blue_arms.size() + " arms", 45, 610);
        g.setColor(originalColor);





        // Set board info
        for (boolean[] row : board) Arrays.fill(row, false);
        for (int i = 0; i < blue_arms.size(); i++) {
            board[blue_arms.get(i).y][blue_arms.get(i).x] = true;
        }
        for (int i = 0; i < red_arms.size(); i++) {
            board[red_arms.get(i).y][red_arms.get(i).x] = true;
        }
//        for (int i = 0; i < yellow_arms.size(); i++) {
//            board[yellow_arms.get(i).y][yellow_arms.get(i).x] = true;
//        }

        // Drawing arms
        for (int i = 0; i < blue_arms.size(); i++) {
            blue_arms.get(i).paint(g);
        }
        for (int i = 0; i < red_arms.size(); i++) {
            red_arms.get(i).paint(g);
        }
//        for (int i = 0; i < yellow_arms.size(); i++) {
//            yellow_arms.get(i).paint(g);
//        }
    }

}
