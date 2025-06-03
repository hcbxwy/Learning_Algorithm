package other;

/**
 * 把一个int的二进制中最右边的1打印出来
 */
public class Issue42_getBinaryRightestOne {

    public static void main(String[] args) {
        System.out.println(getRightestOne(4));
    }

    public static int getRightestOne(int num) {
        int index = 0;
        while ((num & (1 << index)) == 0) {
            index++;
        }
        return index;
    }
}
