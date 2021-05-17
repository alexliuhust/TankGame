import com.alex.drawing.TankFrame;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(35);
            tf.repaint();
        }

    }
}
