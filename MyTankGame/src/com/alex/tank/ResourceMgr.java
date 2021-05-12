package com.alex.tank;

import test.ImageTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage tankL, tankR, tankU, tankD;

    static {
        try {
            tankL = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankL.png"));
            tankR = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankR.png"));
            tankU = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankU.png"));
            tankD = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
