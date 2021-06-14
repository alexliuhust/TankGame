import frame.BattleField;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        BattleField bf = new BattleField();

        while (bf.blue_arms.size() * bf.red_arms.size() != 0) {
            Thread.sleep(25);
            bf.repaint();
        }

        bf.repaint();
        Thread.sleep(1500);
        System.exit(0);
    }
}
