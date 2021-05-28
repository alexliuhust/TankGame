package frame;

import model.Arm;
import model.Hunter;
import model.Knight;
import model.Warrior;

import java.awt.*;
import java.util.List;

public class TestArms {

    public static void _2Warrior_4Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            list.add(new Warrior(3,3, Color.RED, bf));
            list.add(new Warrior(4,3, Color.RED, bf));
            list.add(new Hunter(3,1,  Color.RED, bf));
            list.add(new Hunter(4,1,  Color.RED, bf));
            list.add(new Hunter(3,0,  Color.RED, bf));
            list.add(new Hunter(4,0,  Color.RED, bf));
        } else {
            list.add(new Warrior(3,4, Color.BLUE, bf));
            list.add(new Warrior(4,4, Color.BLUE, bf));
            list.add(new Hunter(3,6,  Color.BLUE, bf));
            list.add(new Hunter(4,6,  Color.BLUE, bf));
            list.add(new Hunter(3,7,  Color.BLUE, bf));
            list.add(new Hunter(4,7,  Color.BLUE, bf));
        }
    }

    public static void _4Knight_2Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            list.add(new Knight(2,1, Color.RED,  bf));
            list.add(new Knight(3,1, Color.RED,  bf));
            list.add(new Knight(4,1, Color.RED,  bf));
            list.add(new Knight(5,1, Color.RED,  bf));

            list.add(new Hunter(3,0, Color.RED,  bf));
            list.add(new Hunter(4,0, Color.RED,  bf));
        } else {
            list.add(new Knight(2,6, Color.BLUE, bf));
            list.add(new Knight(3,6, Color.BLUE, bf));
            list.add(new Knight(4,6, Color.BLUE, bf));
            list.add(new Knight(5,6, Color.BLUE, bf));

            list.add(new Hunter(3,7, Color.BLUE, bf));
            list.add(new Hunter(4,7, Color.BLUE, bf));
        }
    }
}
