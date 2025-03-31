package other;

/**
 * 基于0和1不等概率随机函数实现0和1等概率随机效果
 */
public class Random_02 {

    public static void main(String[] args) {
        int times = 10000;
        int[] arr = new int[2];
        for (int i = 0; i < times; i++) {
            arr[y()]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " 出现了 " + arr[i] + " 次");
        }
    }

    // 固定83%的概率返回1，其余返回0
    private static int x() {
        return Math.random() <= 0.83 ? 1 : 0;
    }

    /**
     * 需求：基于x函数，改成可以等概率返回0和1的y函数
     * 思路：
     * 1.调用两次x()，两次都得到1或者0的概率不固定，重来！
     * 2.第一次得到0，第二次得到1；或者第一次得到1，第二次得到0；这两者的概率肯定相同
     */
    private static int y() {
        int ans;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }
}
