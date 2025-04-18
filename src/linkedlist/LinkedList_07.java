package linkedlist;

/**
 * 两个链表相加
 * 给定两个链表的头节点head1和head2,
 * 认为从左到右是某个数字从低位到高位，返回相加之后的链表
 * 例子 4->3->6 2->5->3
 * 返回 6->8->9
 * 解释 634+352=986
 */
public class LinkedList_07 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(6);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(3);

        ListNode ans = addTwoNumbers(head1, head2);
        while (ans != null) {
            System.out.print(ans.val);
            ans = ans.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = getNodeLen(head1);
        int len2 = getNodeLen(head2);
        ListNode l = len1 > len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        // 是否进位
        int carry = 0;
        int curNum;
        // 先加短链
        while (curS != null) {
            curNum = curL.val + curS.val + carry;
            carry = curNum / 10;
            curL.val = curNum % 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        // 再加长链
        while (curL != null) {
            curNum = curL.val + carry;
            carry = curNum / 10;
            curL.val = curNum % 10;
            last = curL;
            curL = curL.next;
        }
        // 有进位，最后不能漏
        if (carry > 0) {
            last.next = new ListNode(carry);
        }
        return l;
    }

    private static int getNodeLen(ListNode head) {
        int ans = 0;
        while (head != null) {
            head = head.next;
            ans++;
        }
        return ans;
    }
}
