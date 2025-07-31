package tree;

import common.AlgoUtil;
import common.BTNode;

/**
 * 判断一棵树是否平衡二叉树
 * 定义：任意子树左右高度差绝对值不超过1
 *
 * @author hcb
 * @since 2025/7/30 10:27
 */
public class Issue86_isBalanced {

    public static void main(String[] args) {
        AlgoUtil.btChecker(Issue86_isBalanced::isBalanced1, Issue86_isBalanced::isBalanced2);
    }

    // 方案1：用递归套路实现
    public static boolean isBalanced1(BTNode head) {
        return process(head).isBalanced;
    }

    private static class Info {
        int height;
        boolean isBalanced;

        public Info(int h, boolean balanced) {
            height = h;
            isBalanced = balanced;
        }
    }

    private static Info process(BTNode x) {
        if (x == null) {
            return new Info(0, true);
        }
        Info lInfo = process(x.left);
        Info rInfo = process(x.right);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isBalanced = lInfo.isBalanced && rInfo.isBalanced
                && Math.abs(lInfo.height - rInfo.height) <= 1;
        return new Info(height, isBalanced);
    }

    // 方案2：用后序遍历，判断每个节点是否平衡
    public static boolean isBalanced2(BTNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process2(head, ans);
        return ans[0];
    }

    // 计算每个节点高度
    public static int process2(BTNode x, boolean[] ans) {
        if (!ans[0] || x == null) {
            return -1;
        }
        int lHeight = process2(x.left, ans);
        int rHeight = process2(x.right, ans);
        if (Math.abs(lHeight - rHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(lHeight, rHeight) + 1;
    }
}
