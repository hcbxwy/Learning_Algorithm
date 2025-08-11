package greedy;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组arr， 必须把所有的字符串拼接起来， 返回所有可能的拼接结果中，字典序最小的结果
 * 暴力方法：穷举所有组合，然后按字典序排序
 * 贪心策略1：每次从剩余数组中找出字典序最小的子串，然后拼接起来，得到的就在结果。反例：ba，b，该策略结果是bba，实际结果应该是bab < bba
 * 贪心策略2：通过a.b和b.a来比较排序，再拼接起来
 *
 * @author hcb
 * @since 2025/8/11 09:52
 */
public class Issue93_lowestLexicography {

    // 暴力解
    public static String getLowestLexicography1(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        TreeSet<String> set = process1(arr);
        return set.isEmpty() ? "" : set.first();
    }


    // 返回数组中所有字符串的全排列 ["ab", "bc", "abc", "caa"]
    private static TreeSet<String> process1(String[] arr) {
        TreeSet<String> ans = new TreeSet<>();
        if (arr.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < arr.length; i++) {
            String first = arr[i];
            String[] nextArr = removeIndex(i, arr);
            TreeSet<String> nextSet = process1(nextArr);
            for (String str : nextSet) {
                ans.add(first + str);
            }
        }
        return ans;
    }

    // 从数组中删除指定下标的元素，然后返回剩余字符串
    private static String[] removeIndex(int index, String[] arr) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    // 贪心策略
    public static String getLowestLexicography2(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        // 根据a.b和b.a排序
        Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder ans = new StringBuilder();
        for (String str : arr) {
            ans.append(str);
        }
        return ans.toString();
    }

    // maxArrLength数组最大长度，maxStrLength字符串最大长度
    private static String[] randomStrArr(int maxArrLength, int maxStrLength) {
        String[] ans = new String[(int) (Math.random() * maxArrLength + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = randomStr(maxStrLength);
        }
        return ans;
    }

    private static String randomStr(int maxStrLength) {
        // 从26个字母中随机抽取
        char[] cArr = new char[(int) (Math.random() * maxStrLength + 1)];
        for (int j = 0; j < cArr.length; j++) {
            cArr[j] = (char) ('a' + (int) (Math.random() * ('z' - 'a')));
        }
        return String.valueOf(cArr);
    }

    public static void main(String[] args) {
        // System.out.println((char) ('a' + (int) (Math.random() * ('z' - 'a'))));
        // System.out.println(Arrays.toString(randomStrArr(10, 5)));
        // System.out.println(randomStr(5));

        for (int i = 0; i < 100; i++) {
            String[] arr = randomStrArr(5, 5);
            if (!getLowestLexicography1(arr).equals(getLowestLexicography2(arr))) {
                System.out.println("Oops !!!");
            }
        }
        System.out.println("Finished !");
        // System.out.println(Arrays.toString(randomStrArr(10, 10)));
        // System.out.println(getLowestLexicography2(new String[]{"b", "ba"}));
        // System.out.println(getLowestLexicography2(randomStrArr(5, 5)));
    }
}
