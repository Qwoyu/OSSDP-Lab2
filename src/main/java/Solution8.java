/**
 * @description:
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 */
import java.util.ArrayDeque;
import java.util.Deque;
class Solution8 {
    /**
     * 使用迭代版 BFS（队列）淹没一整座岛屿，避免深递归导致的栈溢出风险。
     */
    void bfs(char[][] grid, int sr, int sc) {
        int nr = grid.length;
        int nc = grid[0].length;

        Deque<int[]> q = new ArrayDeque<>();
        // 将起点入队并标记为访问（淹没为水），防止重复入队
        grid[sr][sc] = '0';
        q.offer(new int[] { sr, sc });

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            // 上
            if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                grid[r - 1][c] = '0';
                q.offer(new int[] { r - 1, c });
            }
            // 下
            if (r + 1 < nr && grid[r + 1][c] == '1') {
                grid[r + 1][c] = '0';
                q.offer(new int[] { r + 1, c });
            }
            // 左
            if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                grid[r][c - 1] = '0';
                q.offer(new int[] { r, c - 1 });
            }
            // 右
            if (c + 1 < nc && grid[r][c + 1] == '1') {
                grid[r][c + 1] = '0';
                q.offer(new int[] { r, c + 1 });
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    bfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
