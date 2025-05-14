package tree;

/**
 * 判断一棵树是否搜索二叉树 BST
 * 规则：
 * 1.任意节点大于等于其左子节点最大值，小于其右子节点最小值
 */
public class Issue34_binarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(3);
        root.right.left.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        System.out.println(isBST(root));
    }


    private static boolean isBST(TreeNode root) {
        return process(root).isBST;
    }

    private static NodeInfo process(TreeNode node) {
        if (node == null) {
            return null;
        }
        NodeInfo leftInfo = process(node.left);
        NodeInfo rightInfo = process(node.right);
        int min = node.val;
        int max = node.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean leftIsBST = leftInfo == null || leftInfo.isBST;
        boolean rightIsBST = rightInfo == null || rightInfo.isBST;
        boolean lessRightMin = rightInfo == null || node.val < rightInfo.min;
        boolean moreLeftMax = leftInfo == null || node.val >= leftInfo.max;
        boolean isBST = leftIsBST && rightIsBST && lessRightMin && moreLeftMax;
        return new NodeInfo(min, max, isBST);
    }

    private static class TreeNode {
        private final int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int val) {
            this.val = val;
        }
    }

    private static class NodeInfo {
        Integer min;
        Integer max;
        boolean isBST;

        private NodeInfo(int min, int max, boolean is) {
            this.min = min;
            this.max = max;
            this.isBST = is;
        }
    }
}
