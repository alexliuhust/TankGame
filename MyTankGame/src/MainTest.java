import frame.StartFrame;
import frame.BattleFrame;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
//        realBattle(40, 35);
        test(35);
    }

    private static void realBattle(int a, int b) throws InterruptedException {
        StartFrame sf = new StartFrame();
        while (!sf.closed) {
            Thread.sleep(a);
            sf.repaint();
        }
        sf.setVisible(false);

        BattleFrame tf = new BattleFrame(
                sf.tankTypes.get(sf.tank1selectedIndex),
                sf.tankTypes.get(sf.tank2selectedIndex),
                sf.mapNames.get(sf.mapSelectedIndex));

        sf = null;
        while (true) {
            Thread.sleep(b);
            tf.repaint();
        }
    }

    private static void test(int a) throws InterruptedException {
        BattleFrame tf = new BattleFrame(
                "Light",
                "Light",
                "Broad River");

        while (true) {
            Thread.sleep(a);
            tf.repaint();
        }
    }

}
