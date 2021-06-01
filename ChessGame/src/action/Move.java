package action;

import frame.BattleField;
import model.Arm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Move {

    /**
     * The moving action: tell the arm where is the next step
     * @param x the x-coordinate of the arm
     * @param y the y-coordinate of the arm
     * @param target_x the x-coordinate of the target that this arm intended to go
     * @param target_y the y-coordinate of the target that this arm intended to go
     * @param board the chess board
     * @return the {x-coordinate, y-coordinate} of the next step
     */
    public static int[] normalMove(int x, int y, int target_x, int target_y, boolean[][] board) {
        int[] next_step = new int[] {x, y};

        int dis_x = target_x - x;
        int dis_y = target_y - y;
        if (dis_x == 0 && dis_y == 0) return next_step;

        int[] dir = {0, -1, 0, 1, 0};
        PriorityQueue<int[]> availableCells = new PriorityQueue<>((a, b) -> {
            int dis_a = (a[0] - target_x) * (a[0] - target_x) + (a[1] - target_y) * (a[1] - target_y);
            int dis_b = (b[0] - target_x) * (b[0] - target_x) + (b[1] - target_y) * (b[1] - target_y);
            return dis_a - dis_b;
        });

        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k];
            int ny = y + dir[k + 1];
            if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || board[ny][nx]) continue;

            availableCells.add(new int[] {nx, ny});
        }

        if (availableCells.isEmpty()) return next_step;
        return availableCells.peek();
    }

    /**
     * Get available surrounding positions for the highest-priority target
     * @param x x-coordinate of the arm
     * @param y y-coordinate of the arm
     * @param enemies the enemy list
     * @return available surrounding positions for the highest-priority target
     */
    public static List<int[]> getAvailablePositionsForAssassins(
            int x, int y, BattleField bf, List<Arm> enemies) {
        if (enemies.isEmpty()) {
            return null;
        }

        PriorityQueue<Arm> pq = new PriorityQueue<>((a, b) -> {
            if (a.range != b.range) {
                return b.range - a.range;
            }
            int da = (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
            int db = (x - b.x) * (x - b.x) + (y - b.y) * (y - b.y);
            return db - da;
        });
        pq.addAll(enemies);

        for (Arm arm : pq) {
            List<int[]> availableCells = getAvailableCellsForAssassins(arm.x, arm.y, bf.board);
            if (availableCells.size() == 0) {
                continue;
            }
            return availableCells;
        }
        return null;
    }

    /**
     * Get all available cells around any target
     * @param target_x the x-coordinate of the target that this arm intended to go
     * @param target_y the y-coordinate of the target that this arm intended to go
     * @return available surrounding positions for the target
     */
    private static List<int[]> getAvailableCellsForAssassins(int target_x, int target_y, boolean[][] board) {
        List<int[]> availableCells = new ArrayList<>();

        int[] dir = {0, -1, 0, 1, 0};
        for (int k = 0; k < 4; k++) {
            int x = target_x + dir[k];
            int y = target_y + dir[k + 1];
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && !board[y][x]) {
                availableCells.add(new int[] {x, y});
            }
        }
        return availableCells;
    }
}
