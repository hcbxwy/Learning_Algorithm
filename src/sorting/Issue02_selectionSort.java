package sorting;

import common.AlgoUtil;

/**
 * 排序算法
 * 算法思想：每次从未排序区域选出最小值，放入已排序区域末尾
 * 第1轮：从0~N-1中选出最小值，和0位置的数交换
 * 第2轮：从1~N-1中选出最小值，和1位置的数交换
 * ……
 * 第N-1轮，只剩自己，不需要任何操作了
 */
public class Issue02_selectionSort {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue02_selectionSort::sort);
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
            AlgoUtil.swap(arr, i, min);
        }
    }
}
