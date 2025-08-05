package tree;

import common.AlgoUtil;
import common.BTNode;

/**
 * 判断一棵树是否满二叉树
 * 任意节点左右节点要不全为空，要不全非空
 *
 * @author hcb
 * @since 2025/7/30 10:47
 */
public class Issue87_isFull {

    public static void main(String[] args) {
        // BTNode head = AlgoUtil.randomBT();
        // System.out.println(isFull1(head));
        // AlgoUtil.printBT(head);
        AlgoUtil.btBooleanChecker(Issue87_isFull::isFull1, Issue87_isFull::isFull2);
    }

    // 方法一：整树高度h，节点数n，满足 2^h-1=n
    public static boolean isFull1(BTNode head) {
        Info1 info1 = process(head);
        // System.out.println("height: " + info1.height + ", nodes: " + info1.nodes);
        return Math.pow(2, info1.height) - 1 == info1.nodes;
    }

    private static Info1 process(BTNode x) {
        if (x == null) {
            return new Info1(0, 0);
        }
        int height = 1;
        int nodes = 1;
        if (x.left != null) {
            Info1 lInfo = process(x.left);
            height = Math.max(height, lInfo.height + 1);
            nodes += lInfo.nodes;
        }
        if (x.right != null) {
            Info1 rInfo = process(x.right);
            height = Math.max(height, rInfo.height + 1);
            nodes += rInfo.nodes;
        }
        return new Info1(height, nodes);
    }

    private static class Info1 {
        int height;
        int nodes;

        public Info1(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    // 方法二：任意节点，其左右节点都是满的且高度相等，则必然是满二叉树
    public static boolean isFull2(BTNode head) {
        return process2(head).isFull;
    }

    private static class Info2 {
        boolean isFull;
        int height;

        public Info2(boolean full, int h) {
            isFull = full;
            height = h;
        }
    }

    private static Info2 process2(BTNode x) {
        if (x == null) {
            return new Info2(true, 0);
        }
        Info2 lInfo = process2(x.left);
        Info2 rInfo = process2(x.right);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
        return new Info2(isFull, height);
    }


}
