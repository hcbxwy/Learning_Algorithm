package sorting;

import common.AlgoUtil;

import java.util.Stack;

/**
 * 使用非递归方法实现快排3.0版本
 * 核心思想：自己使用栈代替系统栈
 */
public class Issue40_quickSortByNonRecursion {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue40_quickSortByNonRecursion::quickSort);
        // int[] arr = new int[]{1, 5, 3, 2, 4, 1, 3, 2};
        // AlgoUtil.printArr(arr);
        // quickSort(arr);
        // AlgoUtil.printArr(arr);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(l, r));
        while (!stack.isEmpty()) {
            Info info = stack.pop();
            int left = info.l;
            int right = info.r;
            if (left < right) {
                int p = arr[left + (int) (Math.random() * (right - left + 1))];
                int[] partition = partition(arr, left, right, p);
                stack.push(new Info(left, partition[0] - 1));
                stack.push(new Info(partition[1], right));
            }
        }
    }

    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l;
        int more = r;
        int index = l;
        while (index <= more) {
            if (arr[index] == p) {
                index++;
            } else if (arr[index] < p) {
                swap(arr, index++, less++);
            } else {
                swap(arr, index, more--);
            }
        }
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static class Info {
        private final int l;
        private final int r;

        private Info(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
