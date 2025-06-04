package sorting;

/**
 * 求数组中num的右边有多少个数*2都比num小
 * <a href="https://leetcode.cn/problems/reverse-pairs/">测试链接</a>
 */
public class Issue53_reversePairs2 {

    public static void main(String[] args) {
        int[] arr = new int[]{233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233,
                233, 2000000004};
        System.out.println(reversePairs(arr));
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
        int lp = l;
        int rp = m + 1;
        while (lp <= m) {
            while (rp <= r && arr[lp] > arr[rp] * 2L) {
                rp++;
            }
            ans += rp - m - 1;
            lp++;
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
}
