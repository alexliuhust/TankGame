import frame.StartFrame;
import frame.TankFrame;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        StartFrame sf = new StartFrame();
        while (!sf.closed) {
            Thread.sleep(40);
            sf.repaint();
        }
        sf.setVisible(false);

        TankFrame tf = new TankFrame(
                sf.tankTypes.get(sf.tank1selectedIndex),
                sf.tankTypes.get(sf.tank2selectedIndex),
                sf.mapNames.get(sf.mapSelectedIndex));

        sf = null;
        while (true) {
            Thread.sleep(35);
            tf.repaint();
        }

    }
}
