package frame;

import model.*;

import java.awt.*;
import java.util.List;

public class TestArms {
    public static void _8Warrior(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 0; x <= 7; x++) {
                list.add(new Warrior(x,2, Color.RED, bf));
            }
        } else {
            for (int x = 0; x <= 7; x++) {
                list.add(new Warrior(x,5, Color.BLUE, bf));
            }
        }
    }

    public static void _8Knight(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 0; x <= 7; x++) {
                list.add(new Knight(x,3, Color.RED, bf));
            }
        } else {
            for (int x = 0; x <= 7; x++) {
                list.add(new Knight(x,4, Color.BLUE, bf));
            }
        }
    }

    public static void _8Assassin(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 0; x <= 7; x++) {
                list.add(new Assassin(x,1, Color.RED, bf));
            }
        } else {
            for (int x = 0; x <= 7; x++) {
                list.add(new Assassin(x,6, Color.BLUE, bf));
            }
        }
    }

    public static void _2Warrior_6Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 3; x <= 4; x++) {
                list.add(new Warrior(x,2, Color.RED, bf));
            }
            for (int x = 1; x <= 6; x++) {
                list.add(new Hunter(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 3; x <= 4; x++) {
                list.add(new Warrior(x,5, Color.BLUE, bf));
            }
            for (int x = 1; x <= 6; x++) {
                list.add(new Hunter(x,6,  Color.BLUE, bf));
            }
        }
    }

    public static void _4Warrior_4Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 2; x <= 5; x++) {
                list.add(new Warrior(x,2, Color.RED, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Hunter(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 2; x <= 5; x++) {
                list.add(new Warrior(x,5, Color.BLUE, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Hunter(x,6,  Color.BLUE, bf));
            }
        }
    }

    public static void _4Warrior_4Assassin(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 2; x <= 5; x++) {
                list.add(new Warrior(x,2, Color.RED, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Assassin(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 2; x <= 5; x++) {
                list.add(new Warrior(x,5, Color.BLUE, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Assassin(x,6,  Color.BLUE, bf));
            }
        }
    }

    public static void _2Knight_6Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 3; x <= 4; x++) {
                list.add(new Knight(x,2, Color.RED, bf));
            }
            for (int x = 1; x <= 6; x++) {
                list.add(new Hunter(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 3; x <= 4; x++) {
                list.add(new Knight(x,5, Color.BLUE, bf));
            }
            for (int x = 1; x <= 6; x++) {
                list.add(new Hunter(x,7,  Color.BLUE, bf));
            }
        }
    }

    public static void _4Knight_4Hunter(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 2; x <= 5; x++) {
                list.add(new Knight(x,2, Color.RED, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Hunter(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 2; x <= 5; x++) {
                list.add(new Knight(x,5, Color.BLUE, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Hunter(x,6,  Color.BLUE, bf));
            }
        }
    }

    public static void _4Knight_4Assassin(List<Arm> list, boolean isBlue, BattleField bf) {
        if (!isBlue) {
            for (int x = 2; x <= 5; x++) {
                list.add(new Knight(x,2, Color.RED, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Assassin(x,1,  Color.RED, bf));
            }
        } else {
            for (int x = 2; x <= 5; x++) {
                list.add(new Knight(x,5, Color.BLUE, bf));
            }
            for (int x = 2; x <= 5; x++) {
                list.add(new Assassin(x,6,  Color.BLUE, bf));
            }
        }
    }

}
