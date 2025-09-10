package unionfind;

/**
 * 岛问题
 * 给定一个二维数组matrix，里面的值不是1就是0，
 * 上、下、左、右相邻的1认为是一片岛，
 * 返回matrix中岛的数量
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * @author hcb
 * @since 2025/9/10 15:35
 */
public class Issue100_numberOfIsLand {

    public static int numIslands1(char[][] grid) {
        int ans = 0;
        if (grid == null || grid.length == 0) {
            return ans;
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    ans++;
                    infect(grid, row, col);
                }
            }
        }
        return ans;
    }

    // 感染方法
    private static void infect(char[][] grid, int row, int col) {
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || grid[row][col] != '1') {
            return;
        }
        grid[row][col] = 0;
        infect(grid, row - 1, col);
        infect(grid, row + 1, col);
        infect(grid, row, col - 1);
        infect(grid, row, col + 1);
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        // 首行
        for (int c = 1; c < col; c++) {
            if (grid[0][c] == '1' && grid[0][c - 1] == '1') {
                uf.union(0, c, 0, c - 1);
            }
        }
        // 首列
        for (int r = 1; r < row; r++) {
            if (grid[r][0] == '1' && grid[r - 1][0] == '1') {
                uf.union(r, 0, r - 1, 0);
            }
        }
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (grid[r][c] == '1') {
                    // 上
                    if (grid[r - 1][c] == '1') {
                        uf.union(r, c, r - 1, c);
                    }
                    // 左
                    if (grid[r][c - 1] == '1') {
                        uf.union(r, c, r, c - 1);
                    }
                }
            }
        }

        return uf.sets;
    }

    private static class UnionFind {
        int[] parents;
        int[] sizes;
        int[] help;
        int sets;
        int col;

        private UnionFind(char[][] grid) {
            col = grid[0].length;
            int row = grid.length;
            int len = col * row;
            parents = new int[len];
            sizes = new int[len];
            help = new int[len];
            sets = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int idx = index(i, j);
                        parents[idx] = idx;
                        sizes[idx] = 1;
                        sets++;
                    }
                }
            }
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            // 路径压缩
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int fa = find(i1);
            int fb = find(i2);
            if (fa != fb) {
                if (sizes[fa] >= sizes[fb]) {
                    sizes[fa] += sizes[fb];
                    parents[fb] = fa;
                } else {
                    sizes[fb] += sizes[fa];
                    parents[fa] = fb;
                }
                sets--;
            }
        }
    }
}
