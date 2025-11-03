/**
 * 测试用例设计原则：
 * 采用等价类划分 + 边界值分析原则，覆盖正常输入、异常/退化输入、边界条件。
 * 等价类包含：单岛、多岛、全水、全陆（最小规模）、空网格、单行/单列边界等。
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class L2025001_8_Test {

    // 工具方法：将字符串数组转换为 char[][] 网格（'1'/'0'）
    private static char[][] toGrid(String[] rows) {
        if (rows == null || rows.length == 0) return new char[0][0];
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            g[i] = rows[i].toCharArray();
        }
        return g;
    }

    /**
     * 测试目的：验证题目示例1，单一大岛屿时返回 1。
     * 用例：
     *  11110
     *  11010
     *  11000
     *  00000
     * 期望：1
     */
    @Test
    public void testExample1_singleIsland() {
        Solution8 s = new Solution8();
        char[][] grid = toGrid(new String[]{
                "11110",
                "11010",
                "11000",
                "00000"
        });
        assertEquals(1, s.numIslands(grid));
    }

    /**
     * 测试目的：验证题目示例2，多岛屿时返回 3。
     * 用例：
     *  11000
     *  11000
     *  00100
     *  00011
     * 期望：3
     */
    @Test
    public void testExample2_multipleIslands() {
        Solution8 s = new Solution8();
        char[][] grid = toGrid(new String[]{
                "11000",
                "11000",
                "00100",
                "00011"
        });
        assertEquals(3, s.numIslands(grid));
    }

    /**
     * 测试目的：空网格时返回 0（退化输入）。
     * 用例：[]
     * 期望：0
     */
    @Test
    public void testEmptyGrid_returnsZero() {
        Solution8 s = new Solution8();
        char[][] grid = new char[0][0];
        assertEquals(0, s.numIslands(grid));
    }

    /**
     * 测试目的：全水网格返回 0（等价类：无岛）。
     * 用例：
     *  000
     *  000
     * 期望：0
     */
    @Test
    public void testAllWater_returnsZero() {
        Solution8 s = new Solution8();
        char[][] grid = toGrid(new String[]{
                "000",
                "000"
        });
        assertEquals(0, s.numIslands(grid));
    }

    /**
     * 测试目的：最小规模全陆（1x1）返回 1（边界值）。
     * 用例：
     *  1
     * 期望：1
     */
    @Test
    public void testAllLandSingleCell_returnsOne() {
        Solution8 s = new Solution8();
        char[][] grid = toGrid(new String[]{
                "1"
        });
        assertEquals(1, s.numIslands(grid));
    }

    /**
     * 测试目的：单行与单列的边界情况处理正确（边界值）。
     * 用例1（单行）：10101 -> 期望 3
     * 用例2（单列）：
     *  1
     *  0
     *  1
     *  0
     *  1
     * -> 期望 3
     */
    @Test
    public void testSingleRowAndSingleColumn_boundaries() {
        Solution8 s = new Solution8();
        char[][] row = toGrid(new String[]{
                "10101"
        });
        assertEquals(3, s.numIslands(row));

        char[][] col = new char[][]{
                { '1' },
                { '0' },
                { '1' },
                { '0' },
                { '1' }
        };
        assertEquals(3, s.numIslands(col));
    }

    /**
     * 测试目的：输入被方法就地修改为水，验证不会影响正确性（副作用可接受）。
     * 用例：先调用一次后再次调用应返回 0。
     */
    @Test
    public void testInPlaceMutation_sideEffectIsExpected() {
        Solution8 s = new Solution8();
        char[][] grid = toGrid(new String[]{
                "10",
                "01"
        });
        assertEquals(2, s.numIslands(grid));
        // 再次调用，原 grid 已被淹没，应为 0
        assertEquals(0, s.numIslands(grid));
    }
}
