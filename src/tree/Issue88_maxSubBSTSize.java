package tree;

import common.AlgoUtil;
import common.BTNode;

/**
 * 给定一棵二叉树的头节点head， 返回这颗二叉树中最大的二叉搜索子树的大小
 *
 * @author hcb
 * @since 2025/8/1 15:10
 */
public class Issue88_maxSubBSTSize {

    public static void main(String[] args) {
        // BTNode head = new BTNode(2);
        // head.left = new BTNode(1);
        // head.right = new BTNode(3);
        BTNode head = AlgoUtil.randomBT();
        System.out.println(getMaxSubBSTSize(head));
        AlgoUtil.printBT(head);
    }

    public static int getMaxSubBSTSize(BTNode head) {
        Info info = process(head);
        return info == null ? 0 : info.maxSubBSTSize;
    }

    private static class Info {
        int min;
        int max;
        int maxSubBSTSize;
        int allSize;

        private Info(int mi, int ma, int s, int size) {
            min = mi;
            max = ma;
            maxSubBSTSize = s;
            allSize = size;
        }
    }

    private static Info process(BTNode x) {
        if (x == null) {
            return null;
        }
        Info lInfo = process(x.left);
        Info rInfo = process(x.right);
        int min = x.val;
        int max = x.val;
        int allSize = 1;
        if (lInfo != null) {
            min = Math.min(lInfo.min, min);
            max = Math.max(lInfo.max, max);
            allSize += lInfo.allSize;
        }
        if (rInfo != null) {
            min = Math.min(rInfo.min, min);
            max = Math.max(rInfo.max, max);
            allSize += rInfo.allSize;
        }
        boolean isBST = lInfo == null || lInfo.max <= x.val;
        if (rInfo != null && rInfo.min <= x.val) {
            isBST = false;
        }

        // 1. 包含x时，x必须是BST，结果为左maxSubBSTSize + 右maxSubBSTSize + 1
        int p1 = 0;
        boolean leftBST = lInfo == null || lInfo.maxSubBSTSize == lInfo.allSize;
        boolean rightBST = rInfo == null || rInfo.maxSubBSTSize == rInfo.allSize;
        if (leftBST && rightBST) {
            boolean leftMaxLessX = lInfo == null || lInfo.max < x.val;
            boolean rightMinMoreX = rInfo == null || rInfo.min > x.val;
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = lInfo != null ? lInfo.allSize : 0;
                int rightSize = rInfo != null ? rInfo.allSize : 0;
                p1 = leftSize + rightSize + 1;
            }
        }
        // 2. 不包含x时，取左右较大值
        int p2 = 0;
        if (lInfo != null) {
            p2 = Math.max(lInfo.maxSubBSTSize, p2);
        }
        if (rInfo != null) {
            p2 = Math.max(rInfo.maxSubBSTSize, p2);
        }
        return new Info(min, max, Math.max(p1, p2), allSize);
    }
}
