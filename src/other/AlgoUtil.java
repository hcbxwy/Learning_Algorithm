package other;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * 算法相关的工具类
 */
public class AlgoUtil {

    public static void main(String[] args) {
        int[] arr = randomArr(10, 20);
        printArr(arr);
        swap(arr, 0, 1);
        printArr(arr);
    }

    /**
     * 生成随机数组，长度随机、值也随机
     *
     * @param maxLen 数组最大长度
     * @param maxVal 数组元素最大值
     * @return 随机数组
     */
    public static int[] randomArr(int maxLen, int maxVal) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        return arr;
    }

    /**
     * 打印数组，数组元素之间用空格分隔
     */
    public static void printArr(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * 打印数组，数组元素之间用空格分隔
     */
    public static void printArr(String msg, int[] arr) {
        StringBuilder sb = new StringBuilder(msg);
        for (int a : arr) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    /**
     * 不借助临时变量实现数组元素交换
     * 前提是i和j不能相等
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        // 借助临时变量
        // int t = arr[i];
        // arr[i] = arr[j];
        // arr[j] = t;
    }

    /**
     * 排序算法对数器
     *
     * @param consumer 排序器
     */
    public static void sortedChecker(Consumer<int[]> consumer) {
        int maxLen = 100;
        int maxVal = 1000;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(maxLen, maxVal);
            int[] tempArr = Arrays.copyOf(arr, arr.length);
            consumer.accept(arr);
            if (isNotSorted(arr)) {
                System.out.println("Oops，排序算法有问题！");
                printArr("排序前: ", tempArr);
                printArr("排序后: ", arr);
                return;
            }
        }
        System.out.println("Wow，排序算法正确！！！");
    }

    /**
     * 判断数组是否未排序
     */
    public static boolean isNotSorted(int[] arr) {
        if (arr.length < 2) {
            return false;
        }
        int max = arr[0];
        for (int val : arr) {
            if (val < max) {
                return true;
            }
            max = val;
        }
        return false;
    }
}
