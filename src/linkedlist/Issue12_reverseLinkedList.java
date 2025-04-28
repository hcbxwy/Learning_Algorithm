package linkedlist;

/**
 * 单链表反转
 * 示例：1->2->3->null
 * 反转：null<-1<-2<-3
 * <a href="https://leetcode.cn/problems/reverse-linked-list/description/">测试网址</a>
 */
public class Issue12_reverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
