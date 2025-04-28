package binarysearch;

import other.AlgoUtil;

import java.util.Arrays;

/**
 * 有序数组中找到>=num最左的位置
 */
public class Issue09_findGENumIndex {

    public static void main(String[] args) {
        int maxLen = 10;
        int maxVal = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(maxLen, maxVal);
            Arrays.sort(arr);
            int num = (int) (Math.random() * maxVal);
            int ans = find(arr, num);
            if (ans != test(arr, num)) {
                System.out.println("Oops，排序算法有问题！");
                AlgoUtil.printArr(String.format("num=%d, ans=%d, arr=", num, ans), arr);
                return;
            }
        }
        System.out.println("Wow，排序算法正确！！！");
        // int[] arr = new int[]{2, 3, 5, 6, 6, 8};
        // System.out.println(find(arr, 6));
    }

    public static int find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == num) {
                if (mid == 0 || arr[mid - 1] < num) {
                    return mid;
                }
                right = mid - 1;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 对数器
    public static int test(int[] arr, int num) {
        if (arr == null) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }
}
