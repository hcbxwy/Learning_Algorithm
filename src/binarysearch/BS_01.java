package binarysearch;

import other.AlgoUtil;

import java.util.Arrays;

/**
 * 从一个递增有序数组中找到num，找到返回true，否则false
 */
public class BS_01 {

    public static void main(String[] args) {
        int maxLen = 10;
        int maxVal = 100;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(maxLen, maxVal);
            Arrays.sort(arr);
            int num = (int) (Math.random() * maxVal);
            boolean ans = search(arr, num);
            if (ans != test(arr, num)) {
                System.out.println("Oops，排序算法有问题！");
                AlgoUtil.printArr(String.format("num=%d, ans=%s, arr=", num, ans), arr);
                return;
            }
        }
        System.out.println("Wow，排序算法正确！！！");
    }

    public static boolean search(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 对数器
    public static boolean test(int[] arr, int num) {
        if (arr == null) {
            return false;
        }
        for (int n : arr) {
            if (n == num) {
                return true;
            }
        }
        return false;
    }
}
