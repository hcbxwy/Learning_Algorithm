package tree;

import common.BTNode;

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
        BTNode head = new BTNode(1);
        head.left = new BTNode(2);
        head.left.left = new BTNode(4);
        head.right = new BTNode(3);
        head.right.right = new BTNode(5);

        levelTraversal(head);
    }

    public static void levelTraversal(BTNode head) {
        System.out.print("按层遍历：");
        if (head == null) {
            return;
        }
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(head);
        BTNode cur = null;
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
