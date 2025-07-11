package tree;

/**
 * 递归遍历二叉树：前中后序、递归序
 *
 * @author hcb
 * @since 2025/7/11 11:18
 */
public class Issue75_recursiveTraversalBT {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.print("前序遍历：");
        preOrder(root);

        System.out.println();
        System.out.print("中序遍历：");
        inOrder(root);

        System.out.println();
        System.out.print("后序遍历：");
        posOrder(root);
    }

    // 前序遍历
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + ",");
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + ",");
        inOrder(root.right);
    }

    // 后序遍历
    public static void posOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        posOrder(root.left);
        posOrder(root.right);
        System.out.print(root.val + ",");
    }

    // 递归序：每个节点都被访问3次
    public static void f(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前
        f(root.left);
        // 中
        f(root.right);
        // 后
    }
}
