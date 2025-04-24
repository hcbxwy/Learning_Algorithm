package other;

/**
 * 使用位运算实现加减乘除
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

    // 一个数取反 -num = ~num + 1
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

    public static void main(String[] args) {
        // 测试加法
        // System.out.println(add(100, 99));
        // 测试减法
        System.out.println(minus(10, -10));
    }
}
