package tree;

import common.BTNode;

/**
 * 判断两棵二叉树是否相等
 * <a href="https://leetcode.com/problems/same-tree">测试链接</a>
 */
public class Issue28_isSameTree {

    public static void main(String[] args) {
        BTNode tree1 = new BTNode(1);
        tree1.left = new BTNode(2);
        tree1.right = new BTNode(3);
        BTNode tree2 = new BTNode(1);
        tree2.left = new BTNode(2);
        tree2.right = new BTNode(3);
        System.out.println(isSameTree(tree1, tree2));
    }

    public static boolean isSameTree(BTNode p, BTNode q) {
        if (p == null || q == null) {
            return (p == null) == (q == null);
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
