package other;

/**
 * 如何不用临时变量交换两个数
 */
public class Issue41_swap {

    public static void main(String[] args) {
        int a = 10;
        int b = 1;
        System.out.println("a=" + a + ", b=" + b);
        // 方法一：使用异或的结合律
        // a = a ^ b;
        // b = a ^ b;
        // a = a ^ b;
        // 方法二：使用加减法
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a=" + a + ", b=" + b);
    }

}
