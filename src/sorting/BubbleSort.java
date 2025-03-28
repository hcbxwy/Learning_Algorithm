package sorting;

/**
 * 冒泡排序
 * 核心思想：两两比较，遇到后面比前面大就交换，这样每轮结束都会排好一个最大值
 * 第1轮：从0~N-1，两两比较冒泡，最终确定N-1位置
 * 第2轮：从0~N-2，两两比较冒泡，最终确定N-2位置
 * ……
 * 第N轮，只剩0位置，整个数组有序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 4, 5, 2};
        printArr(arr);
        sort(arr);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
