package tree;

import common.BTNode;

import java.util.Stack;

/**
 * 用非递归方式实现二叉树的前中后序
 *
 * @author hcb
 * @since 2025/7/14 09:40
 */
public class Issue76_UnRecursiveTraversalBT {

    public static void main(String[] args) {
        BTNode head = new BTNode(0);
        head.left = new BTNode(1);
        head.right = new BTNode(2);
        head.left.left = new BTNode(3);
        head.left.right = new BTNode(4);
        head.right.left = new BTNode(5);
        head.right.right = new BTNode(6);

        preOrder(head);
        inOrder(head);
        posOrder(head);
    }

    // 使用一个栈，子节点入栈先右再左
    public static void preOrder(BTNode head) {
        System.out.print("前序遍历: ");
        if (head != null) {
            Stack<BTNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                BTNode cur = stack.pop();
                System.out.print(cur.val + " ");
                // 先入右，再入左
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        System.out.println();
    }

    // 当前节点不为空则左子节点入栈，为空则从栈中弹出一个，并把指针指向右子节点
    public static void inOrder(BTNode head) {
        System.out.print("中序遍历: ");
        Stack<BTNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    // 使用两个栈，从栈1弹出后，先入栈2，然后左右节点进栈1
    public static void posOrder(BTNode head) {
        System.out.print("后序遍历: ");
        if (head != null) {
            Stack<BTNode> stack1 = new Stack<>();
            Stack<BTNode> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()) {
                BTNode cur = stack1.pop();
                stack2.push(cur);
                if (cur.left != null) {
                    stack1.push(cur.left);
                }
                if (cur.right != null) {
                    stack1.push(cur.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
        }

        System.out.println();
    }
}
