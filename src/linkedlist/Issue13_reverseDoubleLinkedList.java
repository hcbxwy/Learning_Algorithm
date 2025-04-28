package linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表反转
 */
public class Issue13_reverseDoubleLinkedList {

    public static void main(String[] args) {
        DoubleListNode head = new DoubleListNode(1);
        DoubleListNode n2 = new DoubleListNode(2);
        DoubleListNode n3 = new DoubleListNode(3);
        head.next = n2;
        n2.prev = head;
        n2.next = n3;
        n3.prev = n2;
        // 打印原链表应该是123和321
        print(head);
        DoubleListNode head2 = reverseList(head);
        // 打印原链表应该是321和123
        print(head2);
    }

    public static DoubleListNode reverseList(DoubleListNode head) {
        if (head == null || (head.next == null && head.prev == null)) {
            return head;
        }
        DoubleListNode next;
        DoubleListNode prev = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void print(DoubleListNode head) {
        if (head == null || (head.next == null && head.prev == null)) {
            System.out.println("null");
        }
        List<Integer> nextList = new ArrayList<>();
        List<Integer> prevList = new ArrayList<>();
        while (head != null) {
            nextList.add(head.value);
            if (head.prev != null) {
                prevList.add(head.prev.value);
            }
            if (head.next == null) {
                prevList.add(head.value);
            }
            head = head.next;
        }

        for (Integer i : nextList) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = prevList.size() - 1; i >= 0; i--) {
            System.out.print(prevList.get(i) + " ");
        }
        System.out.println();
        System.out.println();
    }
}
