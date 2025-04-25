package other;

/**
 * 使用位运算实现加减乘除
 * <a href="https://leetcode.com/problems/divide-two-integers/">除法测试链接</a>
 */
public class Issue21_24_BitAddMinusMultiDiv {

    /**
     * 加法
     * 实现步骤：
     * 1.ab先异或，得到无进位相加 sum
     * 2.ab与运算并左移1位，得到进位，即新的b
     * 3.新的a=sum
     * 4.只要b不等于0，则继续从1开始循环
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b; // 无进位相加
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 求一个数的负数 -num = ~num + 1
    private static int negNum(int num) {
        return add(~num, 1);
    }

    /**
     * 减法
     * a - b = a + (-b)
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 乘法：跟十进制的乘法一样，只是转成二进制相乘
     */
    public static int multi(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = add(ans, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return ans;
    }

    private static boolean isNeg(int num) {
        return num < 0;
    }

    private static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        // 正负得负，负负得正
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            /*
            假设系统最小值是a=-10，最大值是9，b=2
            由于a取反超过了最大值，所以不能直接除，先a+1，再除b，得到c，即 c = (-10 + 1)/2 = -4
            根据c反算回来看距离a有多远，即 a-c*b=-10-(-4*2)=-2，用这个-2再除b等于-1，-1+(-4)就是实际结果
             */
            int c = div(add(a, 1), b);
            return add(c, div(minus(a, multi(c, b)), b));
        } else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {
        // 测试加法
        // System.out.println(add(100, 99));
        // 测试减法
        // System.out.println(minus(10, -10));
        // 测试乘法
        // System.out.println(multi(9, 7));
        // 测试除法
        System.out.println(divide(Integer.MIN_VALUE, 2));
    }
}
