package tree;

/**
 * 根据先序数组和中序数组重建树
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal">测试链接</a>
 */
public class Issue31_buildTreeFromPreorderAndInorder {

    public static void main(String[] args) {
        int[] preorder = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        System.out.println(buildTree(preorder, inorder));
    }

    public static TreeNode buildTree(int[] pre, int[] in) {
        return f(pre, 0, in, 0, in.length);
    }

    public static TreeNode f(int[] pre, int l1, int[] in, int l2, int r2) {
        if (l1 > pre.length - 1 || l2 > r2) {
            return null;
        }
        TreeNode head = new TreeNode(pre[l1]);
        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }
        head.left = f(pre, l1 + 1, in, l2, find - 1);
        head.right = f(pre, l1 + find - l2 + 1, in, find + 1, r2);
        return head;
    }
}
