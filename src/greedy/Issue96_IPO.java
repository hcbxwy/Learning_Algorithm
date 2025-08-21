package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 *
 * @author hcb
 * @since 2025/8/21 15:29
 */
public class Issue96_IPO {

    public static void main(String[] args) {
        int[] costs = new int[]{1, 4, 2, 6};
        int[] profits = new int[]{3, 8, 5, 10};
        int K = 3;
        int M = 1;
        System.out.println(maxMoney1(costs, profits, K, M));
    }


    // 贪心策略：每次选择能做的项目中最赚钱那个
    public static int maxMoney1(int[] costs, int[] profits, int K, int M) {
        // 创建两个堆：花费小根堆和利润大根堆
        PriorityQueue<Project> cost = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        PriorityQueue<Project> profit = new PriorityQueue<>((p1, p2) -> p2.profit - p1.profit);
        for (int i = 0; i < costs.length; i++) {
            cost.add(new Project(costs[i], profits[i]));
        }

        // 每一轮从可选择的项目中选择利润最大那个
        for (int i = 0; i < K; i++) {
            while (!cost.isEmpty() && cost.peek().cost <= M) {
                profit.add(cost.poll());
            }
            // 可选择的项目不够时，就提前退出了
            if (profit.isEmpty()) {
                return M;
            }
            M += profit.poll().profit;
        }
        return M;
    }

    static class Project {
        int cost;
        int profit;

        private Project(int c, int p) {
            cost = c;
            profit = p;
        }
    }
}
