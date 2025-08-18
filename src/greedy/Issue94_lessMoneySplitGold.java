package greedy;

import common.AlgoUtil;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 * @author hcb
 * @since 2025/8/18 16:32
 */
public class Issue94_lessMoneySplitGold {

    public static void main(String[] args) {
        // System.out.println(getLessMoneySplitGold2(new int[]{10, 20, 30}));
        for (int i = 0; i < 10000; i++) {
            int[] arr = AlgoUtil.randomArr(6, 100);
            if (getLessMoneySplitGold1(arr) != getLessMoneySplitGold2(arr)) {
                System.out.println("Oops !");
            }
        }
        System.out.println("Finished !");
    }

    // 贪心策略：使用哈夫曼编码原理，把数组扔进一个小根堆优先队列，每次从队列中取出两个数合并后放回队列，当队列只有一个值时，结果就是合并值累加和
    public static int getLessMoneySplitGold1(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Integer n : arr) {
            queue.add(n);
        }
        int ans = 0;
        while (queue.size() > 1) {
            int sum = queue.poll() + queue.poll();
            ans += sum;
            queue.add(sum);
        }
        return ans;
    }

    // 纯暴力：穷举所有组合，求最小代价
    public static int getLessMoneySplitGold2(int[] arr) {
        return process(arr, 0);
    }

    /**
     * @param cur 当前要处理的数组
     * @param pre 之前的合并行为产生了多少总代价
     * @return 最小代价
     */
    private static int process(int[] cur, int pre) {
        if (cur.length < 2) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cur.length; i++) {
            for (int j = i + 1; j < cur.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(cur, i, j), pre + cur[i] + cur[j]));
            }
        }
        return ans;
    }

    // 合并两个数，并放入新的数组
    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[index++] = arr[k];
            }
        }
        ans[index] = arr[i] + arr[j];
        return ans;
    }


}
