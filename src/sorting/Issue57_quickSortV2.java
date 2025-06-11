package sorting;

import other.AlgoUtil;

/**
 * 快排2.0版本
 * 给定数组arr从L到R，实现＜R的放左边，=R的放中间，＞R的放右边，分区函数每次返回等于区域的开始下标和结束下标
 */
public class Issue57_quickSortV2 {

    public static void main(String[] args) {
        // int[] arr = new int[]{1, 5, 3, 2, 4, 1, 3, 2};
        // AlgoUtil.printArr(arr);
        // quickSort(arr);
        // AlgoUtil.printArr(arr);
        AlgoUtil.sortedChecker(Issue57_quickSortV2::quickSort);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] partition = partition(arr, l, r);
        process(arr, l, partition[0] - 1);
        process(arr, partition[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l;
        int more = r;
        int index = l;
        int x = arr[r];
        while (index <= more) {
            if (arr[index] == x) {
                index++;
            } else if (arr[index] < x) {
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
}
