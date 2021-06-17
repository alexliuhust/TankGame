package resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceMgr {

    public static BufferedImage tankL1, tankR1, tankU1, tankD1;
    public static BufferedImage tankL2, tankR2, tankU2, tankD2;
    public static BufferedImage apL, apR, apU, apD;
    public static BufferedImage atL, atR, atU, atD;
    public static BufferedImage heL, heR, heU, heD;
    public static BufferedImage ironWall, river, grass, brickWall, damagedBrick;
    public static BufferedImage ammo, rearm, hpack;
    public static BufferedImage ap_exp, at_exp, he_exp;

    static {
        try {
            // ==============================================================================================
            tankL1 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL1.png")));
            tankR1 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR1.png")));
            tankU1 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU1.png")));
            tankD1 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD1.png")));

            tankL2 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL2.png")));
            tankR2 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR2.png")));
            tankU2 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU2.png")));
            tankD2 = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD2.png")));

            // ==============================================================================================
            apL = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/apL.png")));
            apR = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/apR.png")));
            apU = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/apU.png")));
            apD = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/apD.png")));

            atL = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/atL.png")));
            atR = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/atR.png")));
            atU = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/atU.png")));
            atD = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/atD.png")));

            heL = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/heL.png")));
            heR = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/heR.png")));
            heU = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/heU.png")));
            heD = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/heD.png")));

            // ==============================================================================================
            grass = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/grass.png")));
            ironWall = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/ironWall.png")));
            river = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/river.png")));
            brickWall = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/brickWall.png")));
            damagedBrick = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/damagedBrick.png")));

            ammo = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/ammo.png")));
            rearm = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/rearm.png")));
            hpack = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/hpack.png")));

            // ==============================================================================================
            ap_exp = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/ap_exp.png")));
            at_exp = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/at_exp.png")));
            he_exp = ImageIO.read(Objects.requireNonNull(
                    ResourceMgr.class.getClassLoader().getResourceAsStream("images/he_exp.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
