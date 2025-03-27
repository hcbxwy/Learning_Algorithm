package other;

/**
 * 位运算
 * 与&：全1为1，否则为0
 * 或|：全0为0，否则为1
 * 非~：0和1都取反
 * 异或^：相同为0，不同为1，两个数进行异或运算等同于进行无进位相加
 * 无符号左移<< : 左移1就是乘以2，左移2就是乘以4……
 * 带符号左移<<<
 * 无符号右移>>: 右移1就是除以2，右移2就是除以4……
 * 带符号右移>>>
 */
public class Bitwise {

    public static void main(String[] args) {
        printIntToBinary(121313);
    }

    public static void printIntToBinary(int num) {
        String binaryString = Integer.toBinaryString(num);
        System.out.println(binaryString);
        System.out.println(String.format("%32s", binaryString).replace(" ", "0"));

        for (int i = 31; i >= 0; i--) {
            System.out.print(1 & (num >> i));
        }
    }
}
