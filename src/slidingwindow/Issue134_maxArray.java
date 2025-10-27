package slidingwindow;

import common.AlgoUtil;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 *
 * @author hcb
 * @since 2025/10/27 10:59
 */
public class Issue134_maxArray {

    public static void main(String[] args) {
        // int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        // int w = 3;
        // int[] ans = maxArray2(arr, w);
        // AlgoUtil.printArr(ans);
        System.out.println("开始测试");
        int times = 10000;
        int maxLen = 10;
        int maxValue = 100;
        for (int i = 0; i < times; i++) {
            int[] arr = AlgoUtil.randomArr(maxLen, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            if (!AlgoUtil.isEqual(maxArray(arr, w), maxArray2(arr, w))) {
                System.out.println("Oops");
                AlgoUtil.printArr(arr);
                return;
            }
        }
        System.out.println("测试通过");
    }

    // 暴力解：每次都循环找出最大值
    public static int[] maxArray(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] ans = new int[N - w + 1];
        int index = 0;
        for (int i = w - 1; i < N; i++) {
            int L = i - w + 1;
            int max = arr[L];
            while (L <= i) {
                max = Math.max(max, arr[L]);
                L++;
            }
            ans[index++] = max;
        }
        return ans;
    }

    // 使用滑动窗口算法实现
    public static int[] maxArray2(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] ans = new int[N - w + 1];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int R = 0; R < N; R++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[R]) {
                queue.pollLast();
            }
            queue.addLast(R);
            // 窗口左边过期
            if (!queue.isEmpty() && R - w == queue.peekFirst()) {
                queue.pollFirst();
            }
            // 形成窗口就开始收集结果
            if (!queue.isEmpty() && R >= w - 1) {
                ans[index++] = arr[queue.peekFirst()];
            }
        }
        return ans;
    }
}
