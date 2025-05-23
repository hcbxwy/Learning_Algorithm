package sorting;

import other.AlgoUtil;

/**
 * 非递归方式实现归并排序
 * 核心思想：使用2的n次方步长来区分左右部分分别排序，然后合并。n从0开始
 */
public class Issue38_mergeSortByNonRecursion {

    public static void main(String[] args) {
        // int[] arr = new int[]{3, 2, 4, 1, 6, 5};
        // AlgoUtil.printArr("排序前：", arr);
        // mergeSort(arr);
        // AlgoUtil.printArr("排序后：", arr);
        AlgoUtil.sortedChecker(Issue38_mergeSortByNonRecursion::mergeSort);
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int n = arr.length;
        while (step < n) {
            int l = 0;
            while (l < n) {
                int m;
                if (l + step < n) {
                    m = l + step - 1;
                } else {
                    m = n - 1;
                }
                // 剪枝
                if (m == n - 1) {
                    break;
                }
                int r;
                if (m + step < n) {
                    r = m + step;
                } else {
                    r = n - 1;
                }
                merge(arr, l, m, r);
                // 剪枝
                if (r == n - 1) {
                    break;
                } else {
                    l = r + 1;
                }
            }
            // 剪枝
            if (step > n / 2) {
                break;
            }
            step *= 2;
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int lIndex = l;
        int rIndex = m + 1;
        int tIndex = 0;
        while (lIndex <= m && rIndex <= r) {
            temp[tIndex++] = arr[lIndex] <= arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= m) {
            temp[tIndex++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            temp[tIndex++] = arr[rIndex++];
        }
        tIndex = 0;
        while (l <= r) {
            arr[l++] = temp[tIndex++];
        }
    }
}
