package sorting;

import other.AlgoUtil;

/**
 * 求数组的最小和，要求时间复杂度O(nlogn)
 * 示例：arr=[2,3,1,4]
 * 第0位2前面没有比它小的，最小和为0
 * 第1位3前面比它小的数是2，最小和为2
 * 第2位1前面没有比它小的数，最小和为0
 * 第3位4前面比它小的数有2,3,1，最小和为6
 * 最终数组的最小和=0+2+0+6=8
 * <a href="https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469">测试链接</a>
 */
public class Issue51_minSum {

    public static void main(String[] args) {
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(100, 100000);
            int[] arr2 = AlgoUtil.copy(arr);
            long minSum = minSum(arr);
            long testMinSum = testMinSum(arr2);
            if (minSum != testMinSum) {
                System.out.println("算错了, minSum=" + minSum + ", testMinSum=" + testMinSum);
                AlgoUtil.printArr(arr);
                break;
            }
        }
        // Scanner in = new Scanner(System.in);
        // int n = in.nextInt();
        // int[] arr = new int[n];
        // for (int i = 0; i < n; i++) {
        //     arr[i] = in.nextInt();
        // }
        // System.out.println(minSum(arr));
    }

    public static long minSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return minSum(arr, 0, arr.length - 1);
    }

    // 数组从l到r上求最小和并排序
    public static long minSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = (l + r) / 2;
        return minSum(arr, l, m) + minSum(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static long merge(int[] arr, int l, int m, int r) {
        // 计算最小和，这里要用long，否则会溢出
        long ans = 0;
        for (int i = l, j = m + 1, sum = 0; j <= r; j++) {
            while (i <= m && arr[i] <= arr[j]) {
                sum += arr[i++];
            }
            ans += sum;
        }

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

    // 对数器：使用双层循环求最小和
    public static long testMinSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                    ans += arr[j];
                }
            }
        }
        return ans;
    }
}
