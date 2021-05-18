package test;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageTest {

    @Test
    void test() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int random_number = rand.nextInt(3);
            System.out.print(random_number + ", ");
        }

    }
}
