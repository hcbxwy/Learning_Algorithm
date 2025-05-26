package other;

import java.util.Objects;

/**
 * 理解递归，获取数组的最大值
 */
public class Issue50_arrayGetMax {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] arr = AlgoUtil.randomArr(10, 10);
            if (!Objects.equals(getMax(arr), testGetMax(arr))) {
                System.out.println("不通过测试");
                AlgoUtil.printArr(arr);
            }
        }
        System.out.println("通过测试");
    }

    public static Integer getMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return getMax(arr, 0, arr.length - 1);
    }

    public static int getMax(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int lMax = getMax(arr, l, mid);
        int rMax = getMax(arr, mid + 1, r);
        return Math.max(lMax, rMax);
    }

    public static Integer testGetMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int max = arr[0];
        for (int n : arr) {
            max = Math.max(max, n);
        }
        return max;
    }
}
