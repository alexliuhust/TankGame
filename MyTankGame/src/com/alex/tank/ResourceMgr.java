package com.alex.tank;

import test.ImageTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceMgr {

    public static BufferedImage tankL1, tankR1, tankU1, tankD1;
    public static BufferedImage tankL2, tankR2, tankU2, tankD2;
    public static BufferedImage ironWall, river;

    static {
        try {
            tankL1 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankL1.png")));
            tankR1 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankR1.png")));
            tankU1 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankU1.png")));
            tankD1 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankD1.png")));

            tankL2 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankL2.png")));
            tankR2 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankR2.png")));
            tankU2 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankU2.png")));
            tankD2 = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/tankD2.png")));

            ironWall = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/ironWall.png")));
            river = ImageIO.read(Objects.requireNonNull(
                    ImageTest.class.getClassLoader().getResourceAsStream("images/river.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
