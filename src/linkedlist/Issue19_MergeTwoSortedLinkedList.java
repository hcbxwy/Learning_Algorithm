package linkedlist;

/**
 * 合并两个有序链表（默认升序）
 * 给定两个有序链表的头节点head1和head2.
 * 返回合并之后的大链表，要求依然有序
 * 例子 1->2 2->3
 * 返回 1->2->2->3
 * <a href="https://leetcode.com/problems/merge-two-sorted-lists">测试链接</a>
 */
public class Issue19_MergeTwoSortedLinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(4);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode head3 = mergeTwoLists(head1, head2);
        while (head3 != null) {
            System.out.println(head3.val);
            head3 = head3.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

}
