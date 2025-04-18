package linkedlist;

/**
 * 用单链表实现栈
 * 栈即后进先出
 */
public class LinkedList_04 {

    ListNode first;

    public static void main(String[] args) {
        LinkedList_04 list = new LinkedList_04();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
    }

    // 每次加到链首
    public void add(int val) {
        if (first == null) {
            first = new ListNode(val);
        } else {
            ListNode t = new ListNode(val);
            t.next = first;
            first = t;
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
