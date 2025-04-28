package linkedlist;

/**
 * 单链表中k个节点小组内逆序，不够k个节点时不处理
 * 如：链表 1➡2➡3➡4➡5，k=2
 * 逆序后：2➡1➡4➡3➡5
 * <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/">测试链接</a>
 */
public class Issue17_reverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        head.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        n4.next = new ListNode(5);
        head = reverseKGroup(head, 2);
        System.out.println(head.val);
    }

    /**
     * 思路：
     * 1.确定k个节点的子链（链头和链尾）
     * 2.对子链进行逆序
     * 3.子链相接，前一个链尾接后一个链头
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        // 凑不齐k个节点
        if (end == null) {
            return head;
        }
        // 第一组凑齐了
        head = end;
        reverse(start, end);
        // 上一组尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    // 获取k个节点的尾节点
    private static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // 小组内逆序
    private static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
