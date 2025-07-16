package sorting;

import common.AlgoUtil;

/**
 * 快排1.0版本
 * 给定数组arr从L到R，实现＜R的放左边，=R的放中间，＞R的放右边，分区函数每次返回等于区域最后的下标
 */
public class Issue56_quickSortV1 {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue56_quickSortV1::quickSort);
        // int[] arr = new int[]{2, 4, 3, 1, 5, 1, 3};
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

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int x = arr[R];
        int mid = partition(arr, L, R, x);
        process(arr, L, mid - 1);
        process(arr, mid + 1, R);
    }

    public static int partition(int[] arr, int L, int R, int x) {
        int less = L;
        int more = R;
        int index = L;
        while (index <= more) {
            if (arr[index] == x) {
                index++;
            } else if (arr[index] < x) {
                swap(arr, less++, index++);
            } else {
                swap(arr, index, more--);
            }
        }
        return more;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
