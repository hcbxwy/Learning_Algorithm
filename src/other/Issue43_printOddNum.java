package other;

/**
 * 数组中只有一种数出现奇数次，其他数都出现了偶数次，请找出这种数
 * 要求空间复杂度O(1)，时间复杂度O(n)
 */
public class Issue43_printOddNum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        System.out.println(getOddNum(arr));
    }

    public static int getOddNum(int[] arr) {
        int ans = 0;
        for (int j : arr) {
            ans ^= j;
        }
        return ans;
    }
}
