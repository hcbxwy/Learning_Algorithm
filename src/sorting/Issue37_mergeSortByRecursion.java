package sorting;

import other.AlgoUtil;

/**
 * 使用递归方式实现归并排序
 * 核心思想：递归对数组进行二分成左右两边各自有序，再把左右合并，最终就是有序
 */
public class Issue37_mergeSortByRecursion {

    public static void main(String[] args) {
        // int[] arr = new int[]{4, 1, 2, 1, 3};
        // AlgoUtil.printArr("排序前：", arr);
        // mergeSort(arr);
        // AlgoUtil.printArr("排序后：", arr);
        AlgoUtil.sortedChecker(Issue37_mergeSortByRecursion::mergeSort);
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 确保数组arr从l到r上有序
    private static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // 取中间位置 (l+r)/2
        int m = l + ((r - l) >> 1);
        // 确保m左边有序
        process(arr, l, m);
        // 确保m右边有序
        process(arr, m + 1, r);
        // 合并左边、m和右边
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        // 保证arr从l到r之间有序，先用一个临时数组保存排序结果，再覆盖回arr数组l到r部分
        int[] temp = new int[r - l + 1];
        int tp = 0;
        int lp = l;
        int rp = m + 1;
        while (lp <= m && rp <= r) {
            temp[tp++] = arr[lp] <= arr[rp] ? arr[lp++] : arr[rp++];
        }
        // 左边剩下的放进数组
        while (lp <= m) {
            temp[tp++] = arr[lp++];
        }
        // 右边剩下的放进数组
        while (rp <= r) {
            temp[tp++] = arr[rp++];
        }
        // 把临时数组放进原数组l到r部分
        tp = 0;
        while (tp < temp.length) {
            arr[l++] = temp[tp++];
        }
    }

}
