package stack;

/**
 * 用数组实现不超过固定大小的栈
 */
public class Issue47_fixedStack {

    public static void main(String[] args) {
        FixedStack stack = new FixedStack(3);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        // System.out.println(stack.peek());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    static class FixedStack {
        // 栈容量
        private final int capacity;
        // 存储数据的数组
        private final int[] stack;
        // 栈大小
        private int size;

        public FixedStack(int capacity) {
            this.capacity = capacity;
            this.stack = new int[capacity];
        }

        public Integer push(int val) {
            if (size == capacity) {
                // 表示栈满了
                return null;
            }
            stack[size++] = val;
            return val;
        }

        public Integer pop() {
            if (size == 0) {
                // 表示栈空了
                return null;
            }
            return stack[size-- - 1];
        }

        public Integer peek() {
            if (size == 0) {
                // 表示栈空了
                return null;
            }
            return stack[size - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }
}
