package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树中的某个节点，返回该节点的后继节点
 * 后继结点是指当前节点在中序遍历中的下一个节点
 *
 * @author hcb
 * @since 2025/7/23 10:00
 */
public class Issue81_successorNode {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Node ans = getSuccessorNode(head, head.left);
        System.out.println(ans.value);
    }

    public static class Node {
        Integer value;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            value = val;
        }
    }

    // x有右节点：则后继结点就是右子树最左的节点
    // x没有右节点，则分两种情况：1️⃣x是其父节点的左节点时：后继结点就是其父节点；
    // 2️⃣x是其父节点的右节点时：后继节点就是其最远祖先节点
    public static Node getSuccessorNode(Node head, Node x) {
        if (head == null) {
            return null;
        }
        if (x.right != null) {
            return getMostLeft(x.right);
        }
        Node parent = x.parent;
        while (parent != null && parent.right == x) {
            x = parent;
            parent = parent.parent;
        }
        return parent;
    }

    // 获取当前子树的最左节点
    public static Node getMostLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 土办法：先进行中序遍历放进数组，再遍历数组确定x的后继节点
    public static Node successorNode1(Node head, Node x) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        process(head, list);
        Node ans = null;
        for (Node node : list) {
            if (node == x) {
                int xIndex = list.indexOf(x);
                if (xIndex + 1 < list.size()) {
                    ans = list.get(xIndex + 1);
                }
            }
        }
        return ans;
    }

    public static void process(Node cur, List<Node> list) {
        if (cur == null) {
            return;
        }
        process(cur.left, list);
        list.add(cur);
        process(cur.right, list);
    }
}
