package binarysearch;

import other.AlgoUtil;

/**
 * 给定一个无序数组，返回一个局部最小值
 */
public class BS_03 {

    public static void main(String[] args) {
        int maxLen = 10;
        int maxVal = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(maxLen, maxVal);
            int num = (int) (Math.random() * maxVal);
            int ans = find(arr);
            if (!test(arr, ans)) {
                System.out.println("Oops，排序算法有问题！");
                AlgoUtil.printArr(String.format("num=%d, ans=%d, arr=", num, ans), arr);
                return;
            }
        }
        System.out.println("Wow，排序算法正确！！！");
        // int[] arr = new int[]{9, 6, 3};
        // System.out.println(test(arr, 2));
    }

    // 给定一个无序数组，返回一个局部最小值的下标
    public static int find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        // 如果数组只有一个元素，则该元素就是局部最小
        if (N == 1) {
            return 0;
        }
        // 判断第一个元素是否局部最小
        if (arr[0] <= arr[1]) {
            return 0;
        }
        // 判断最后一个元素是否局部最小
        if (arr[N - 1] <= arr[N - 2]) {
            return N - 1;
        }
        // 如果第一个和最后一个都不是局部最小，则局部最小必然出现现0~N-1之间
        int left = 1;
        int right = N - 2;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= arr[mid - 1] && arr[mid] <= arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 对数器
    public static boolean test(int[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return index == -1;
        }
        int N = arr.length;
        // 如果数组只有一个元素，则该元素就是局部最小
        if (N == 1) {
            return index == 0;
        }
        // 判断第一个元素是否局部最小
        if (arr[0] <= arr[1]) {
            return index == 0;
        }
        // 判断最后一个元素是否局部最小
        if (arr[N - 1] <= arr[N - 2]) {
            return index == N - 1;
        }
        return arr[index] <= arr[index - 1] && arr[index] <= arr[index + 1];
    }
}
