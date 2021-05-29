import frame.BattleField;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        BattleField bf = new BattleField();

        while (true) {
            Thread.sleep(45);
            bf.repaint();
        }
    }
}
