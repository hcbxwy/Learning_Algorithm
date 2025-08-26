package unionfind;

import java.io.*;

/**
 * 使用数组实现并查集
 * 链接：<a href="https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372">...</a>
 * <br>
 * 给定一个没有重复值的整形数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计一种叫UnionFind的结构，并提供以下两个操作。
 * boolean isSameSet(int a, int b): 查询a和b这两个数是否属于一个集合
 * void union(int a, int b): 把a所在的集合与b所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
 * <br>
 * [要求]
 * 如果调用isSameSet和union的总次数逼近或超过O(N)，请做到单次调用isSameSet或union方法的平均时间复杂度为O(1)
 * <br>
 * 输入描述：
 * 第一行两个整数N, M。分别表示数组大小、操作次数
 * 接下来M行，每行有一个整数opt
 * 若opt = 1，后面有两个数x, y，表示查询(x, y)这两个数是否属于同一个集合
 * 若opt = 2，后面有两个数x, y，表示把x, y所在的集合合并在一起
 * 示例：
 * 4 5
 * 1 1 2
 * 2 2 3
 * 2 1 3
 * 1 1 1
 * 1 2 3
 * <br>
 * 输出描述：
 * 对于每个opt = 1的操作，若为真则输出"Yes"，否则输出"No"
 * No
 * Yes
 * Yes
 *
 * @author hcb
 * @since 2025/8/26 14:14
 */
public class Issue98_UnionFindOfArray {

    private static final int MAX = 1000001;
    private static final int[] parents = new int[MAX];
    private static final int[] sizes = new int[MAX];
    private static final int[] help = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }

    }

    private static void init(int n) {
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    private static int find(int cur) {
        int hIndex = 0;
        while (cur != parents[cur]) {
            help[hIndex++] = cur;
            cur = parents[cur];
        }
        for (hIndex--; hIndex >= 0; hIndex--) {
            parents[help[hIndex]] = cur;
        }
        return cur;
    }

    private static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        int aFather = find(a);
        int bFather = find(b);
        if (aFather != bFather) {
            if (sizes[aFather] >= sizes[bFather]) {
                sizes[aFather] += sizes[bFather];
                parents[bFather] = parents[aFather];
            } else {
                sizes[bFather] += sizes[aFather];
                parents[aFather] = parents[bFather];
            }
        }
    }
}
