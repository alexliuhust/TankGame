package frame;

import action.CareerBonus;
import effect.Effect;
import model.Arm;
import model.Hunter;
import model.Knight;
import model.Warrior;

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

    public List<Effect> effects = new ArrayList<>();

    public boolean[][] board = new boolean[8][8];

    public BattleField() {
//        TestArms._8Warrior(         red_arms, false,    this);
        TestArms._4Warrior_4Hunter( red_arms, false ,   this);
//        TestArms._8Assassin(        red_arms, false ,   this);

//        TestArms._8Knight(         blue_arms, true,     this);
//        TestArms._4Knight_4Hunter( blue_arms, true ,    this);
        TestArms._8Assassin(        blue_arms, true ,   this);


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
        paintFieldInfo(g);
        paintBoard(g);
        updateBoardInfo();
        paintArmsAndEffects(g);
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
     * Update occupation info of the chess board
     */
    private void updateBoardInfo() {
        for (boolean[] row : board) Arrays.fill(row, false);
        for (int i = 0; i < blue_arms.size(); i++) {
            board[blue_arms.get(i).y][blue_arms.get(i).x] = true;
        }
        for (int i = 0; i < red_arms.size(); i++) {
            board[red_arms.get(i).y][red_arms.get(i).x] = true;
        }
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
