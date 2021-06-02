package frame;

import common.CareerBonus;
import effect.Effect;
import model.Arm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class BattleField extends Frame {

    public static final int GAME_WIDTH = 580, GAME_HEIGHT = 680;

    public List<Arm> blue_arms = new ArrayList<>();
    public List<Arm> red_arms = new ArrayList<>();

    public List<Effect> effects = new ArrayList<>();

    public boolean[][] board = new boolean[8][8];

    public BattleField() {
        TestArms.deployArms(red_arms, false, this, new int[] {4,0,0,0});
        TestArms.deployArms(blue_arms, true, this, new int[] {0,4,0,0});

        CareerBonus.careerBonus(red_arms);
        CareerBonus.careerBonus(blue_arms);

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
//        paintBoardOccupied(g);
        paintFieldInfo(g);
        paintBoard(g);
        paintArmsAndEffects(g);
    }

    /**
     * Paint occupation of the board
     */
    private void paintBoardOccupied(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(Color.PINK);

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j]) {
                    g.fillRect(57 + j * 60,107 + i * 60,46, 46);
                }
            }
        }
        g.setColor(originalColor);
    }

    /**
     * Paint field info
     */
    private void paintFieldInfo(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Red  Player has " + red_arms.size() + " arms", 45, 75);
        g.drawString("Blue Player has " + blue_arms.size() + " arms", 45, 610);
        g.setColor(originalColor);
    }

    /**
     * Paint chess board
     */
    private void paintBoard(Graphics g) {
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
    }

    /**
     * Paint arms and effects
     */
    private void paintArmsAndEffects(Graphics g) {
        // Drawing arms
        for (int i = 0; i < blue_arms.size(); i++) {
            blue_arms.get(i).paint(g);
        }
        for (int i = 0; i < red_arms.size(); i++) {
            red_arms.get(i).paint(g);
        }

        // Drawing effects
        for (int i = 0; i < effects.size(); i++) {
            effects.get(i).paint(g, this);
        }
    }
}
