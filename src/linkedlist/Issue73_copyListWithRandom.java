package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/">测试链接</a>
 *
 * @author hcb
 * @since 2025/7/9 09:41
 */
public class Issue73_copyListWithRandom {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n1.random = n3;
        n2.next = n3;
        n3.random = n2;
        // Node newHead = copyRandomListByMap(n1);
        Node newHead = copyRandomList(n1);
        System.out.println(newHead);
    }

    // 不使用容器
    private static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 先把复制节点串到原链表
        // 原：1 -> 2 -> 3 -> null
        // 新：1 -> 1' -> 2 -> 2' -> 3 -> 3'
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 设置复制节点的random，按对遍历
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 新老链表分离
        Node ans = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }

    // 使用容器HashMap
    private static Node copyRandomListByMap(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
