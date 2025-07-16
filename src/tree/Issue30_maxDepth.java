package tree;

import common.BTNode;

/**
 * 返回一颗二叉树的最大深度
 * 树的最大深度是指从根节点到最远叶节点的最长路径上的节点数
 * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree">测试链接</a>
 */
public class Issue30_maxDepth {

    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.left.left = new BTNode(4);
        root.right = new BTNode(3);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(BTNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
