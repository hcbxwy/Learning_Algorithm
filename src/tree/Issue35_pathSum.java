package tree;

import common.BTNode;

/**
 * 判断二叉树是否存在达标路径和
 * 路径和是指从根节点出发到达叶节点所经过的节点之和
 * 题意是给定一个根节点root，和一个目标值sum，判断该二叉树是否存在路径和等于sum，返回值boolean
 * <a href="https://leetcode.com/problems/path-sum">测试链接</a>
 */
public class Issue35_pathSum {

    public static boolean hasPathSum(BTNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum);
    }

    // 方案1：累减
    public static boolean process(BTNode node, int rest) {
        // 叶子节点
        if (node.left == null && node.right == null) {
            return node.val == rest;
        }
        // 非叶子节点
        boolean ans = node.left != null && process(node.left, rest - node.val);
        ans |= node.right != null && process(node.right, rest - node.val);
        return ans;
    }

    // 方案2：累加
    public static boolean process2(BTNode node, int sum, int targetSum) {
        // 叶子节点
        if (node.left == null && node.right == null) {
            return sum + node.val == targetSum;
        }
        sum += node.val;
        boolean ans = node.left != null && process2(node.left, sum, targetSum);
        ans |= node.right != null && process2(node.right, sum, targetSum);
        return ans;
    }

}
