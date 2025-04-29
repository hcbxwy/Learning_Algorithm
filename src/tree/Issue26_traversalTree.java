package tree;

/**
 * 二叉树的前中后序遍历打印
 */
public class Issue26_traversalTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        // 前序遍历，期望结果：1 2 4 5 3 6 7
        preorder(root);
        System.out.println();
        // 中序遍历，期望结果：4 2 5 1 6 3 7
        inorder(root);
        System.out.println();
        // 后序遍历，期望结果：4 5 2 6 7 3 1
        postorder(root);
    }

    public static void preorder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void postorder(TreeNode node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }
}
