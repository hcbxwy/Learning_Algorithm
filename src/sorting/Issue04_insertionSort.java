package sorting;

import common.AlgoUtil;

/**
 * 插入排序
 * 核心思想：每次从未排序区域中取出一个数插入到已排序区域
 * 第1轮：0~0已排序，从1开始，和0~0比较，如果小就交换，最终0~1有序
 * 第2轮：从2开始，从右到左和0~1比较，如果小就交换，并且往前移，最终0~2有序
 * ……
 * 第N轮：从N-1开始，从右到左和0~N-2比较，如果小就交换，并且往前移，最终0~N-1有序
 */
public class Issue04_insertionSort {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue04_insertionSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    AlgoUtil.swap(arr, j, j - 1);
                }
            }
        }
    }
}
