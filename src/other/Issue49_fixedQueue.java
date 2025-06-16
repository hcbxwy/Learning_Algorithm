package other;

/**
 * 怎样用数组实现不超过固定大小的队列
 */
public class Issue49_fixedQueue {

    public static void main(String[] args) {
        FixedQueue queue = new FixedQueue(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.poll();
        queue.poll();
        queue.add(5);
        queue.add(6);
        queue.add(0);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    static class FixedQueue {
        private final int[] queue;
        private int head;
        private int tail;
        private int size;

        public FixedQueue(int capacity) {
            this.queue = new int[capacity];
            this.tail = -1;
        }

        public Integer add(int val) {
            // 满了
            if (size == queue.length) {
                return null;
            }
            tail = tail == queue.length - 1 ? 0 : tail + 1;
            queue[tail] = val;
            size++;
            return val;
        }

        public Integer poll() {
            if (size == 0) {
                return null;
            }
            int ans = queue[head];
            head = head == queue.length - 1 ? 0 : head + 1;
            size--;
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
