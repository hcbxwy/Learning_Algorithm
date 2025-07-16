package tree;

import common.BTNode;

/**
 * 判断二叉树是否镜面树（自对称）
 * <a href="https://leetcode.com/problems/symmetric-tree">测试链接</a>
 */
public class Issue29_SymmetricTree {

    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.left.left = new BTNode(3);
        root.left.right = new BTNode(4);
        root.right = new BTNode(2);
        root.right.left = new BTNode(4);
        root.right.right = new BTNode(3);
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(BTNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(BTNode t1, BTNode t2) {
        if (t1 == null || t2 == null) {
            return (t1 == null) == (t2 == null);
        }
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
