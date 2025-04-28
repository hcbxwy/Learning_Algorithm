package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 * 示例
 * 有3个链表：
 * 1->2->3
 * 2->3
 * 1->4
 * 合并后链表为
 * 1->1->2->2->3->3->4
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/">测试链接</a>
 */
public class Issue26_MergeKSortedLinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(4);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(3);
        head3.next.next = new ListNode(5);
        ListNode[] lists = new ListNode[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;
        ListNode head4 = mergeKLists(lists);
        while (head4 != null) {
            System.out.println(head4.val);
            head4 = head4.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }

        ListNode head = heap.poll();
        if (head != null && head.next != null) {
            heap.add(head.next);
        }
        ListNode pre = head;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            if (cur.next != null) {
                heap.add(cur.next);
            }
            pre.next = cur;
            pre = pre.next;
        }
        return head;
    }

    private static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

}
