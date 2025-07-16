package sorting;

import common.AlgoUtil;

/**
 * 求数组有多少个逆序对
 */
public class Issue52_reversePairs {

    public static void main(String[] args) {
        int times = 10000;
        int maxLen = 10;
        int maxValue = 10;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(maxLen, maxValue);
            int[] arr2 = AlgoUtil.copy(arr);
            if (reversePairs(arr) != test(arr2)) {
                System.out.println("算法有问题");
                AlgoUtil.printArr(arr);
                break;
            }
        }
    }

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        // 先统计
        int ans = 0;
        int lp = m;
        int rp = r;
        while (lp >= l) {
            while (rp > m && arr[lp] <= arr[rp]) {
                rp--;
            }
            ans += rp - m;
            lp--;
        }
        // 再合并
        int[] help = new int[r - l + 1];
        int lIndex = l;
        int rIndex = m + 1;
        int hIndex = 0;
        while (lIndex <= m && rIndex <= r) {
            help[hIndex++] = arr[lIndex] <= arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= m) {
            help[hIndex++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            help[hIndex++] = arr[rIndex++];
        }
        hIndex = 0;
        while (hIndex < help.length) {
            arr[l++] = help[hIndex++];
        }
        return ans;
    }

    public static int test(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
