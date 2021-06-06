package frame;

import model.*;

import java.awt.*;
import java.util.List;

public class TestArms {

    private static final int[] frontLine_middle = {3,4,2,5,1,6,0,7};
    private static final int[] jumpLine_middle = {0,7,1,6,2,5,3,4};

    public static void deployArms(List<Arm> list, boolean isBlue, BattleField bf, int[] nums) {
        Color armColor = isBlue ? Color.BLUE : Color.RED;

        int numWarrior = Math.min(nums[0], 8);
        int numKnight = Math.min(nums[1], 8);
        int numPriest = Math.min(nums[2], 8);
        int numHunter = Math.min(nums[3], 8);
        int numMage = Math.min(nums[4], 8);
        int numAssassin = Math.min(nums[5], 8);


        // Melee
        int frontY = isBlue ? 4 : 3;
        int pos = 0;
        for (int i = 0; i < numWarrior; i++) {
            list.add(new Warrior(frontLine_middle[pos], frontY, armColor, bf));
            pos++;
        }
        if (pos == 8 || ( pos > 0 && numKnight + pos >= 8)) {
            pos = 0;
            frontY = isBlue ? 5 : 2;
        }
        for (int i = 0; i < numKnight; i++) {
            list.add(new Knight(frontLine_middle[pos], frontY, armColor, bf));
            pos++;
        }

        // Range
        int rangeY = isBlue ? 6 : 1;
        pos = 0;
        for (int i = 0; i < numPriest; i++) {
            list.add(new Priest(frontLine_middle[pos], rangeY, armColor, bf));
            pos++;
        }

        if (pos == 8 || (pos > 0 && numHunter + pos >= 8)) {
            pos = 0;
            rangeY = isBlue ? 7 : 0;
        }

        for (int i = 0; i < numHunter; i++) {
            list.add(new Hunter(frontLine_middle[pos], rangeY, armColor, bf));
            pos++;
        }

        if (pos == 8 || (pos > 0 && numMage + pos >= 8)) {
            pos = 0;
            rangeY = isBlue ? 7 : 0;
        }

        for (int i = 0; i < numMage; i++) {
            list.add(new Mage(frontLine_middle[pos], rangeY, armColor, bf));
            pos++;
        }


        // Jump
        int jumpY = isBlue ? 7 : 0;
        pos = 0;
        for (int i = 0; i < numAssassin; i++) {
            list.add(new Assassin(jumpLine_middle[pos], jumpY, armColor, bf));
            pos++;
        }

    }

}
