package linkedlist;

/**
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 * @author hcb
 * @since 2025/7/3 11:31
 */
public class Issue70_getNextDownMidNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // head.next.next.next.next.next = new ListNode(6);
        ListNode upMidNode = getNextDownMidNode(head);
        System.out.println(upMidNode != null ? upMidNode.val : null);
    }

    private static ListNode getNextDownMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            pre = pre.next;
            fast = fast.next.next;
        }
        return pre;
    }
}
