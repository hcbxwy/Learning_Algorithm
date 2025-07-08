package linkedlist;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * @author hcb
 * @since 2025/7/8 09:57
 */
public class Issue72_listPartition {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(4);
        // ListNode newHead = partitionByArray(head, 2);
        ListNode newHead = partition(head, 2);
        while (newHead != null) {
            System.out.print(newHead.val + "->");
            newHead = newHead.next;
        }
    }

    // 不用容器实现
    public static ListNode partition(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 小于区域的头尾
        ListNode smallH = null, smallT = null;
        // 等于区域的头尾
        ListNode equalH = null, equalT = null;
        // 大于区域的头尾
        ListNode largeH = null, largeT = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            // 这一步很关键，要把尾巴断掉
            head.next = null;
            if (head.val < k) {
                if (smallH == null) {
                    smallH = head;
                    smallT = head;
                } else {
                    smallT.next = head;
                    smallT = smallT.next;
                }
            } else if (head.val == k) {
                if (equalH == null) {
                    equalH = head;
                    equalT = head;
                } else {
                    equalT.next = head;
                    equalT = equalT.next;
                }
            } else {
                if (largeH == null) {
                    largeH = head;
                    largeT = head;
                } else {
                    largeT.next = head;
                    largeT = largeT.next;
                }
            }
            head = next;
        }
        // 小于区域、等于区域和大于区域串起来
        // 如果有小于区域
        if (smallT != null) {
            smallT.next = equalH;
            // 接下来谁去连大于区域的头
            equalT = equalT == null ? smallT : equalT;
        }
        // 一定是等于区域连大于区域的头
        if (equalT != null) {
            equalT.next = largeH;
        }

        return smallH != null ? smallH : equalH != null ? equalH : largeH;
    }


    // 使用数组实现
    public static ListNode partitionByArray(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 把链表放入数组
        ListNode[] arr = new ListNode[len];
        cur = head;
        int index = 0;
        while (cur != null) {
            arr[index++] = cur;
            cur = cur.next;
        }
        // 对数组进行分区
        partition(arr, k);
        // 把数组转成链表
        head = arr[0];
        cur = head;
        for (int i = 1; i < len; i++) {
            cur.next = arr[i];
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    public static void partition(ListNode[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        int index = 0;
        while (index <= right) {
            ListNode cur = arr[index];
            if (cur.val == k) {
                index++;
            } else if (cur.val < k) {
                swap(arr, index++, left++);
            } else {
                swap(arr, index, right--);
            }
        }
    }

    public static void swap(ListNode[] arr, int i, int j) {
        ListNode t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
