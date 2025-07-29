package common;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

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
     * 生成随机二叉树
     *
     * @param maxLevel 最大层数
     * @param maxVal 节点最大值
     * @return 二叉树头节点
     */
    public static BTNode randomBT(int maxLevel, int maxVal) {
        return generateBT(1, maxLevel, maxVal);
    }

    /**
     * 生成随机二叉树
     * 默认5层，节点最大值10
     *
     * @return 二叉树头节点
     */
    public static BTNode randomBT() {
        return generateBT(1, 3, 10);
    }

    /**
     * 打印二叉树
     *
     * @param head 头结点
     */
    public static void printBT(BTNode head) {
        BinaryTreePrinter.print(head);
    }

    private static BTNode generateBT(int curLevel, int maxLevel, int maxVal) {
        // 有30%的概率产生空节点，当前层数大于最大层数时结束
        if (Math.random() < 0.3 || curLevel > maxLevel) {
            return null;
        }
        BTNode head = new BTNode(randomInt(maxVal));
        head.left = generateBT(curLevel + 1, maxLevel, maxVal);
        head.right = generateBT(curLevel + 1, maxLevel, maxVal);
        return head;
    }

    private static int randomInt(int maxVal) {
        return (int) (Math.random() * maxVal);
    }

    /**
     * 复制数组
     * @param arr 旧数组
     * @return 新数组
     */
    public static int[] copy(int[] arr) {
        int[] temp = new int[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        return temp;
    }

    /**
     * 打印数组，数组元素之间用空格分隔
     */
    public static void printArr(int[] arr) {
        for (int n : arr) {
            System.out.print(n + ",");
        }
        System.out.println();
    }

    /**
     * 打印数组，数组元素之间用空格分隔
     */
    public static void printArr(String msg, int[] arr) {
        StringBuilder sb = new StringBuilder(msg);
        for (int a : arr) {
            sb.append(a).append(",");
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
     * 二叉树对数器
     *
     * @param f1 需要测试的方法
     * @param f2 用于对数的方法
     */
    public static void btChecker(Function<BTNode, Boolean> f1, Function<BTNode, Boolean> f2) {
        for (int i = 0; i < 100000; i++) {
            BTNode head = randomBT();
            Boolean f1Ans = f1.apply(head);
            Boolean f2Ans = f2.apply(head);
            if (f1Ans != f2Ans) {
                System.out.println("Oops! 未通过测试！！！");
                System.out.println("f1: " + f1Ans + ", f2: " + f2Ans);
                printBT(head);
                break;
            }
        }
        System.out.println("Perfect! 通过测试！");
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
