package resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {

    public static BufferedImage assassin_icon, hunter_icon, knight_icon, mage_icon, priest_icon, warrior_icon;

    static {
        try {

            assassin_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/assassin_icon.png")));
            hunter_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/hunter_icon.png")));
            knight_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/knight_icon.png")));
            mage_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/mage_icon.png")));
            priest_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/priest_icon.png")));
            warrior_icon = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/warrior_icon.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
