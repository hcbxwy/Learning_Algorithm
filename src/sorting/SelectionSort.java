package sorting;

/**
 * 排序算法
 * 算法思想：每次从未排序区域选出最小值，放入已排序区域末尾
 * 第1轮：从0~N-1中选出最小值，和0位置的数交换
 * 第2轮：从1~N-1中选出最小值，和1位置的数交换
 * ……
 * 第N-1轮，只剩自己，不需要任何操作了
 */
public class SelectionSort {

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
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
