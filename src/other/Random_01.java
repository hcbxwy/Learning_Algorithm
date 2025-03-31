package other;

/**
 * 学习随机事件，等概率事件等
 * Math.random()会等概率返回double [0,1)
 * 练习题：
 * 1.给定一个等概率返回[10,25]的f()函数,如何基于f函数实现g函数,要求等概率返回[15,20]。
 */
public class Random_01 {

    public static void main(String[] args) {
        int times = 10000;
        int[] arr = new int[21];
        for (int i = 0; i < times; i++) {
            arr[g()]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " 出现了 " + arr[i] + " 次");
        }
    }

    // 等概率返回[a,b]
    private static int f(int a, int b) {
        return (int) (Math.random() * (b - a + 1)) + a;
    }

    /**
     * 需求：基于f函数等概率返回[15,20]
     * 解题思路：
     * 1、把f函数改造成等概率返回0和1的函数f01()
     * 2、根据f01()实现等概率返回[0,5]的函数f2
     * 3、根据f2() + 15就是等概率返回[15,20]
     */
    private static int g() {
        return f2() + 15;
    }

    // 把f函数改造成等概率返回0和1的函数f01()
    private static int f01(int a, int b) {
        // 取[a,b]的中间值: (a+b)/2, a+b容易溢出，所以可改成 a + ((b-a) >> 1)
        int mid = a + ((b - a) >> 1);
        if ((b - a + 1) % 2 == 0) {
            return f(a, b) <= mid ? 0 : 1;
        }
        int ans;
        do {
            ans = f(a, b);
        } while (ans == mid);
        return ans < mid ? 0 : 1;
    }

    /**
     * 根据f01()实现等概率返回[0,5]
     * 思路：
     * 1.最大值是5，可以用3个bit实现，即000-111，代表了[0,7]
     * 2.由于f01()可以等概率返回0和1，则可以等概率返回[0,7]
     * 3.等概率返回0-7时，大于5则重来，这样可以等概率返回[0,5]
     */
    private static int f2() {
        int ans;
        do {
            ans = (f01(2, 5) << 2) + (f01(2, 5) << 1) + f01(2, 5);
        } while (ans > 5);
        return ans;
    }
}
