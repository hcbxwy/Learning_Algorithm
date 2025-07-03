package linkedlist;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 *
 * @author hcb
 * @since 2025/7/3 10:20
 */
public class Issue67_getUpMidNode {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        // head.next.next.next.next = new Node(5);
        // head.next.next.next.next.next = new Node(6);
        Node upMidNode = getUpMidNode(head);
        System.out.println(upMidNode != null ? upMidNode.val : null);
    }

    private static Node getUpMidNode(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static class Node {
        private final int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
