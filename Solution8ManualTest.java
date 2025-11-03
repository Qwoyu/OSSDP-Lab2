public class Solution8ManualTest {
    // 便于在本地无依赖环境下快速自检（非作业提交所需）
    public static void main(String[] args) {
        Solution8 s = new Solution8();

        char[][] grid1 = toGrid(new String[]{
                "11110",
                "11010",
                "11000",
                "00000"
        });
        int r1 = s.numIslands(grid1);
        System.out.println("Example1 result = " + r1 + " (expected 1)");

        char[][] grid2 = toGrid(new String[]{
                "11000",
                "11000",
                "00100",
                "00011"
        });
        int r2 = s.numIslands(grid2);
        System.out.println("Example2 result = " + r2 + " (expected 3)");

        if (r1 != 1 || r2 != 3) {
            System.out.println("Manual test FAILED");
            System.exit(1);
        }
        System.out.println("Manual test PASSED");
    }

    private static char[][] toGrid(String[] rows) {
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            g[i] = rows[i].toCharArray();
        }
        return g;
    }
}
