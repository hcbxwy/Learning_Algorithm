package linkedlist;

import java.util.Objects;
import java.util.Stack;

/**
 * 判断链表是否回文结构
 * 有两种实现
 * 1.使用容器，空间复杂度O(n)
 * 2.不使用容器，空间复杂度O(1)
 *
 * @author hcb
 * @since 2025/7/7 10:53
 */
public class Issue71_isPalindromeList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(isPalindromeByStack(head) == isPalindrome(head));
        // System.out.println(isPalindrome(head));
    }

    // 使用栈实现
    public static boolean isPalindromeByStack(ListNode head) {
        // 先压入栈
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        // 遍历链表，每遍历一个就从栈弹出一个，不想等就表示不是回文结构
        cur = head;
        while (cur != null) {
            if (!Objects.equals(cur.val, stack.pop())) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 不使用容器实现
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 使用快慢指针确定中点位置，如果是偶数长度，取上中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中点往后位置逆序
        ListNode cur = slow.next;
        ListNode pre = slow;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 左右双指针遍历比较，如果不想等就不是回文结构
        ListNode left = head;
        ListNode right = pre;
        boolean res = true;
        while (left.next != null) {
            if (right == null || !Objects.equals(left.val, right.val)) {
                res = false;
                break;
            }
            left = left.next;
            right = right.next;
        }
        // 把逆序部分调整回来
        cur = pre;
        pre = null;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return res;
    }
}
