package tree;

/**
 * 返回一颗二叉树的最大深度
 * 树的最大深度是指从根节点到最远叶节点的最长路径上的节点数
 * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree">测试链接</a>
 */
public class Issue30_maxDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
