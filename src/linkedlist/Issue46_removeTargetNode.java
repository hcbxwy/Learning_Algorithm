package linkedlist;

/**
 * 把一个链表中给定值都删除
 * 示例：给定链表1→2→2→3→2→4→4→2，删除2，结果为 1→3→4→4
 */
public class Issue46_removeTargetNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next = new ListNode(2);
        int target = 2;
        ListNode newHead = removeTarget(head, target);
        while (newHead != null) {
            System.out.print(newHead.val + "→");
            newHead = newHead.next;
        }
    }

    public static ListNode removeTarget(ListNode head, int x) {
        while (head != null && head.val == x) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode ans = cur;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == x || next.val != x) {
                cur = next;
            } else {
                cur.next = next.next;
            }
        }
        return ans;
    }
}
