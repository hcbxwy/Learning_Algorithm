package sorting;

/**
 * 统计数组区间和个数
 * 给定数组nums，求子数组累加和在区间[lower,upper]的个数
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * <a href="https://leetcode.cn/problems/count-of-range-sum/description/">测试链接</a>
 */
public class Issue54_countRangeSum {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0};
        int lower = 0;
        int upper = 0;
        System.out.println(countRangeSum(arr, lower, upper));
    }

    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 转换成前缀和
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            if (sum[l] <= upper && sum[l] >= lower) {
                return 1;
            } else {
                return 0;
            }
        }
        int m = l + ((r - l) >> 1);
        int leftCount = process(sum, l, m, lower, upper);
        int rightCount = process(sum, m + 1, r, lower, upper);
        int merge = merge(sum, l, m, r, lower, upper);
        return leftCount + rightCount + merge;
    }

    public static int merge(long[] sum, int l, int m, int r, int lower, int upper) {
        // 先统计
        int ans = 0;
        int L = l;
        int R = l;
        for (int i = m + 1; i <= r; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (R <= m && sum[R] <= max) {
                R++;
            }
            while (L <= m && sum[L] < min) {
                L++;
            }
            ans += R - L;
        }
        // 再合并
        long[] help = new long[r - l + 1];
        int lIndex = l;
        int rIndex = m + 1;
        int hIndex = 0;
        while (lIndex <= m && rIndex <= r) {
            help[hIndex++] = sum[lIndex] <= sum[rIndex] ? sum[lIndex++] : sum[rIndex++];
        }
        while (lIndex <= m) {
            help[hIndex++] = sum[lIndex++];
        }
        while (rIndex <= r) {
            help[hIndex++] = sum[rIndex++];
        }
        hIndex = 0;
        while (hIndex < help.length) {
            sum[l++] = help[hIndex++];
        }
        return ans;
    }
}
