package tree;

/**
 * 判断一棵树是否平衡二叉树
 * 平衡的定义：任意节点的左右子节点高度差不超过1
 * <a href="https://leetcode.com/problems/balanced-binary-tree/submissions/1631719492/">测试链接</a>
 */
public class Issue33_balancedBinaryTree {

    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private static NodeInfo process(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, true);
        }
        NodeInfo leftInfo = process(node.left);
        NodeInfo rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new NodeInfo(height, isBalanced);
    }

    private static class NodeInfo {
        int height;
        boolean isBalanced;

        public NodeInfo(int h, boolean is) {
            height = h;
            isBalanced = is;
        }
    }
}
