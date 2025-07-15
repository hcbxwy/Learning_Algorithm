package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树按层遍历
 *
 * @author hcb
 * @since 2025/7/15 11:25
 */
public class Issue77_levelTraversal {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.right = new TreeNode(3);
        head.right.right = new TreeNode(5);

        levelTraversal(head);
    }

    public static void levelTraversal(TreeNode head) {
        System.out.print("按层遍历：");
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
}
