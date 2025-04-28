package linkedlist;

/**
 * 用单链表实现队列
 * 队列即先进先出 FIFO
 */
public class Issue14_linkedListToQueue {

    ListNode first;
    ListNode last;

    public static void main(String[] args) {
        Issue14_linkedListToQueue list = new Issue14_linkedListToQueue();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
    }

    // 每次加到链尾
    public void add(int val) {
        if (last == null) {
            last = new ListNode(val);
            first = last;
        } else {
            last.next = new ListNode(val);
            last = last.next;
        }
    }

    // 每次从链首弹出
    public Integer poll() {
        if (first == null) {
            return null;
        }
        int ans = first.val;
        first = first.next;
        return ans;
    }
}
