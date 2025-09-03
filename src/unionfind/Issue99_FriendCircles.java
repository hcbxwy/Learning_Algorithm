package unionfind;

/**
 * 朋友圈 测试链接：<a href="https://leetcode.com/problems/number-of-provinces/description/">...</a>
 * 有个nxn的矩阵，每个元素不是0就是1，上下左右方向直接相邻为1就是同一个朋友圈，返回多少个朋友圈
 * 示例1：有两个朋友圈
 * 1 1 0
 * 1 1 0
 * 0 0 1
 * 示例2：有3个朋友圈
 * 1 0 0
 * 0 1 0
 * 0 0 1
 *
 *
 * @author hcb
 * @since 2025/9/3 15:37
 */
public class Issue99_FriendCircles {

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.sets;
    }

    private static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int sets;

        private UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 找到祖先节点，并进行路径压缩
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        // 小挂大
        private void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                if (size[fa] >= size[fb]) {
                    size[fa] += size[fb];
                    parent[fb] = fa;
                } else {
                    size[fb] += size[fa];
                    parent[fa] = fb;
                }
                sets--;
            }
        }
    }
}
