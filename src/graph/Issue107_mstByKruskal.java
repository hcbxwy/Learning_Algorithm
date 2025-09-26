package graph;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小生成树之Kruskal
 * 测试链接 : https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
 * 题目：
 * 一个有n户人家的村庄，有m条路连接着。村里现在要修路，每条路都有一个代价，
 * 现在请你帮忙计算下，最少需要花费多少的代价，就能让这n户人家连接起来。
 * 输入描述：
 * 输入第一行，两个整数n,m;
 * 接下来m行，每行三个整数a,b,c，表示有路连接编号为a和b的人家，修路要花费的代价为c。
 * 数据保证能将每户人家都连接起来。
 * 注意重边的情况。 n≤10000 , m≤100000，0<c≤10000。
 * 输出描述：
 * 输出最小的花费代价使得这n户人家连接起来。
 * 示例：
 * 输入
 * 3 3
 * 1 3 3
 * 1 2 1
 * 2 3 1
 * 输出
 * 2
 *
 * @author hcb
 * @since 2025/9/23 14:37
 */
public class Issue107_mstByKruskal {

    private static final int MAX_M = 100001;
    private static final int[][] edges = new int[MAX_M][3];

    // 处理输入
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            // 按代价排序
            Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
            build(n);
            int ans = 0;
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    ans += edge[2];
                }
            }
            out.println(ans);
            out.flush();
        }
    }

    // 并查集结构
    private static final int MAX_N = 10001;
    private static final int[] father = new int[MAX_N];
    private static final int[] size = new int[MAX_N];
    private static final int[] help = new int[MAX_N];

    // 初始化并查集
    private static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    // 查找祖先，并路径压缩
    private static int find(int i) {
        int hi = 0;
        while (i != father[i]) {
            i = father[i];
            help[hi++] = i;
        }
        for (hi--; hi >= 0; hi--) {
            father[help[hi]] = i;
        }
        return i;
    }

    // 合并：小挂大
    private static boolean union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if (fi != fj) {
            if (size[fi] >= size[fj]) {
                father[fj] = fi;
                size[fi] += size[fj];
            } else {
                father[fi] = fj;
                size[fj] += size[fi];
            }
            return true;
        }
        return false;
    }
}
