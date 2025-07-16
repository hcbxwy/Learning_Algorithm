package sorting;

import common.AlgoUtil;

/**
 * 基数排序
 */
public class Issue66_RadixSort {

    public static void main(String[] args) {
        // int[] arr = new int[]{103, 20, 11, 23, 22};
        // radixSort(arr);
        // AlgoUtil.printArr(arr);
        AlgoUtil.sortedChecker(Issue66_RadixSort::radixSort);
    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int digit = maxBit(arr);
        int[] help = new int[arr.length];
        int radix = 10;
        for (int i = 1; i <= digit; i++) {
            // 统计每一位个数，如个位数上1的数有多少个、2的数有多少个……
            int[] count = new int[radix];
            for (int k : arr) {
                int index = getDigit(k, i);
                count[index]++;
            }
            // 统计每一位前缀和，如个位数上<=0有多少个、<=1有多少个……
            for (int j = 1; j < radix; j++) {
                count[j] += count[j - 1];
            }
            // 从右到左遍历arr，根据前缀和确定每个元素排序后的位置
            for (int j = arr.length - 1; j >= 0; j--) {
                int index = getDigit(arr[j], i);
                help[count[index] - 1] = arr[j];
                count[index]--;
            }
            // 把排序后结果放回arr，结束本轮排序
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }
        }
    }

    public static int maxBit(int[] arr) {
        // 找出最大值，确定有几位
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        // 确定有几位
        int ans = 0;
        while (max != 0) {
            ans++;
            max /= 10;
        }
        return ans;
    }

    public static int getDigit(int num, int digit) {
        return (num / (int) Math.pow(10, digit - 1)) % 10;
    }
}
