package linkedlist;

/**
 * 用双链表实现双端队列
 * 双端队列是指可以在两端进行插入和删除操作
 * 基本操作：
 * 入队：addFirst, addLast
 * 出队：pollFirst, pollLast
 * 判空：isEmpty
 * 获取队头/队尾元素: peekFirst, peekLast
 */
public class Issue16_doubleLinkedListToDeque {

    DoubleListNode first;
    DoubleListNode last;


    public static void main(String[] args) {
        Issue16_doubleLinkedListToDeque list = new Issue16_doubleLinkedListToDeque();
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        System.out.println(list.peekFirst());
        System.out.println(list.peekLast());
        System.out.println(list.pollFirst());
        System.out.println(list.pollLast());
        System.out.println(list.peekFirst());
        System.out.println(list.peekLast());

    }

    public void addFirst(int val) {
        DoubleListNode node = new DoubleListNode(val);
        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    public void addLast(int val) {
        DoubleListNode node = new DoubleListNode(val);
        if (isEmpty()) {
            first = last = node;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
    }

    public Integer pollFirst() {
        if (isEmpty()) {
            return null;
        }
        DoubleListNode ans = first;
        first = first.next;
        return ans.value;
    }

    public Integer pollLast() {
        if (isEmpty()) {
            return null;
        }
        DoubleListNode ans = last;
        last = last.prev;
        return ans.value;
    }

    public Integer peekFirst() {
        return isEmpty() ? null : first.value;
    }

    public Integer peekLast() {
        return isEmpty() ? null : last.value;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
