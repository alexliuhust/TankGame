package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    @Test
    void test() {
        try {
//            BufferedImage image = ImageIO.read(new File(""));
//            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tank01.png"));
            assertNotNull(image2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
