package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化与反序列化
 * 只有先后遍历可以反序列化，中序遍历是无法反序列化的
 *
 * @author hcb
 * @since 2025/7/15 10:10
 */
public class Issue78_serializeAndReconstruct {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.right = new TreeNode(3);
        head.right.right = new TreeNode(5);
        Queue<String> preQueue = preSerialize(head);
        System.out.println("先序遍历序列化：" + Arrays.toString(preQueue.toArray()));
        TreeNode preHead = preReconstruct(preQueue);
        System.out.println("先序遍历反序列化：" + preHead);

        Queue<String> posQueue = posSerialize(head);
        System.out.println("后序遍历序列化：" + Arrays.toString(posQueue.toArray()));
        TreeNode posHead = posReconstruct(posQueue);
        System.out.println("后序遍历反序列化：" + posHead);

        Queue<String> levelQueue = levelSerialize(head);
        System.out.println("按层遍历序列化：" + Arrays.toString(levelQueue.toArray()));
        TreeNode levelHead = levelReconstruct(levelQueue);
        System.out.println("按层遍历反序列化：" + levelHead);
    }

    // 先序遍历的序列化
    public static Queue<String> preSerialize(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        preS(head, queue);
        return queue;
    }

    private static void preS(TreeNode head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.val));
            preS(head.left, queue);
            preS(head.right, queue);
        }
    }

    // 先序遍历反序列化
    public static TreeNode preReconstruct(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preR(queue);
    }

    private static TreeNode preR(Queue<String> queue) {
        String val = queue.poll();
        if (val == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(val));
        head.left = preR(queue);
        head.right = preR(queue);
        return head;
    }

    public static Queue<String> posSerialize(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        posS(head, queue);
        return queue;
    }

    private static void posS(TreeNode cur, Queue<String> queue) {
        if (cur == null) {
            queue.add(null);
        } else {
            posS(cur.left, queue);
            posS(cur.right, queue);
            queue.add(String.valueOf(cur.val));
        }
    }

    public static TreeNode posReconstruct(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        // 左右中 逆序改成  中右左
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posR(stack);
    }

    private static TreeNode posR(Stack<String> stack) {
        String val = stack.pop();
        if (val == null) {
            return null;
        }
        // 中右左
        TreeNode head = new TreeNode(Integer.parseInt(val));
        head.right = posR(stack);
        head.left = posR(stack);
        return head;
    }

    // 层级遍历的序列化
    public static Queue<String> levelSerialize(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                ans.add(null);
            } else {
                ans.add(String.valueOf(cur.val));
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return ans;
    }

    public static TreeNode levelReconstruct(Queue<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        TreeNode head = generateNode(list.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = generateNode(list.poll());
            cur.right = generateNode(list.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }

    private static TreeNode generateNode(String val) {
        return val == null ? null : new TreeNode(Integer.parseInt(val));
    }

}
