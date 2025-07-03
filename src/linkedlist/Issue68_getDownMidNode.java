package linkedlist;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 *
 * @author hcb
 * @since 2025/7/3 10:38
 */
public class Issue68_getDownMidNode {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        // head.next.next.next.next = new Node(5);
        // head.next.next.next.next.next = new Node(6);
        Node downMidNode = getDownMidNode(head);
        System.out.println(downMidNode != null ? downMidNode.val : null);
    }

    private static Node getDownMidNode(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static class Node {
        private final int val;
        private Node next;

        private Node(int val) {
            this.val = val;
        }
    }
}
