package linkedlist;

/**
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 *
 * @author hcb
 * @since 2025/7/3 11:17
 */
public class Issue69_getPreUpMidNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        ListNode upMidNode = getPreUpMidNode(head);
        System.out.println(upMidNode != null ? upMidNode.val : null);
    }

    private static ListNode getPreUpMidNode(ListNode head) {
        // 节点数不够3个时，中点是head，前一个是null
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode pre = head;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            pre = pre.next;
            fast = fast.next.next;
        }
        return pre;
    }
}
