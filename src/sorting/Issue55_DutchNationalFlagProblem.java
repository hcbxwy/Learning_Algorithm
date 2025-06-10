package sorting;

import other.AlgoUtil;

/**
 * 荷兰国旗问题
 * 给定一个无序数组arr和一个目标值x，要求：
 * 1.数组中<=x的数放左边，>x的数放右边
 * 2.数组中<x的数放左边，=x的数放中间，>x的数放右边
 */
public class Issue55_DutchNationalFlagProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 4, 1};
        int x = 3;
        AlgoUtil.printArr(arr);
        process1(arr, x);
        AlgoUtil.printArr(arr);
    }

    // 数组中<=x的数放左边，>x的数放右边
    public static void process1(int[] arr, int x) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int R = -1;
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (cur <= x) {
                swap(arr, i, R + 1);
                R++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
