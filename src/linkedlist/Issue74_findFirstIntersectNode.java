package linkedlist;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点；如果不相交，返回null。
 *
 * @author hcb
 * @since 2025/7/10 16:07
 */
public class Issue74_findFirstIntersectNode {

    public static void main(String[] args) {
        // 两个都无环
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        System.out.println("两个都无环：" + findFirstIntersectNode(head1, head2));
        // 两个都无环，相交点在2
        head2.next.next = head1.next;
        System.out.println("两个都无环，相交点在2：" + findFirstIntersectNode(head1, head2).val);
        // 一个有环，一个无环，不相交
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = head1.next.next;
        System.out.println("一个有环，一个无环，不相交：" + findFirstIntersectNode(head1, head2));

    }

    public static ListNode findFirstIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);
        // 两个都无环，可能相交，可能不相交
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 两个都有环，可能相交，可能不相交
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一个链表有环，另一个无环，那么肯定不相交
        return null;
    }

    // 给定一个头结点，返回该链表第一个入环节点，若无环返回null
    // 使用快慢指针，若快指针遇到null，则肯定无环；若快指针与慢指针相遇，则快指针回到头节点，并且步数改为1，当快慢指针最次相遇就是第一个入环节点
    public static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode f = head.next.next;
        ListNode s = head.next;
        while (f != s) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            f = f.next.next;
            s = s.next;
        }
        f = head;
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return f;
    }

    // 求两个链表都无环时，相交的第一个节点，若无返回null
    public static ListNode noLoop(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        // 先确定链表长度，长链表要先走到跟短链表一样长度后，再同步走
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 求两个链表都有环时，相交的第一个节点，若无则返回null
    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        // 若入环节点相等，求第一个相交节点（跟两链表都无环时求相交节点类似）
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 若入环节点不相等，那么从一个入环节点走一圈，如果遇到另一个入环节点，则表示相交，否则不相交
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
