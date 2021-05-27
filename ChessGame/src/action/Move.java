package action;

public class Move {

    public static int[] getNextStep(int x, int y, int target_x, int target_y, boolean[][] board) {
        int[] next_step = new int[] {x, y};

        int dis_x = target_x - x;
        int dis_y = target_y - y;
        if (dis_x == 0 && dis_y == 0) return next_step;

        // Straight Direction
        if (dis_x * dis_y == 0) {
            if (dis_x == 0) {
                if (dis_y > 0 && !board[y + 1][x]) next_step[1] = y + 1;
                else if (dis_y < 0 && !board[y - 1][x]) next_step[1] = y - 1;
            } else {
                if (dis_x > 0 && !board[y][x + 1]) next_step[0] = x + 1;
                else if (dis_x < 0 && !board[y][x - 1]) next_step[0] = x - 1;
            }
        }

        // The target is on the left
        if (dis_x < 0) {
            if (Math.abs(dis_y) > Math.abs(dis_x)) {
                if (dis_y > 0 && !board[y + 1][x]) next_step[1] = y + 1;
                else if (dis_y < 0 && !board[y - 1][x]) next_step[1] = y - 1;
                else if (!board[y][x - 1]) next_step[0] = x - 1;
            } else {
                if (!board[y][x - 1]) next_step[0] = x - 1;
                else if (dis_y > 0 && !board[y + 1][x]) next_step[1] = y + 1;
                else if (dis_y < 0 && !board[y - 1][x]) next_step[1] = y - 1;
            }
        }

        // The target is on the right
        else {

        }



//        System.out.println(next_step[0] + "," + next_step[1]);
        return next_step;
    }

}
