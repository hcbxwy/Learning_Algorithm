package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 *
 * @author hcb
 * @since 2025/8/20 11:22
 */
public class Issue95_bestArrange {

    static class Program {
        int start;
        int end;

        private Program(int s, int e) {
            start = s;
            end = e;
        }
    }

    // 暴力解：每一种情况都尝试
    private static int bestArrange1(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(0, 0, arr);
    }

    // 当前时间点来到timeline，已经安排了count场次数，找出剩下项目中最多可安排几场
    private static int process(int timeline, int count, Program[] arr) {
        if (arr.length == 0) {
            return count;
        }
        int max = count;
        for (int i = 0; i < arr.length; i++) {
            if (timeline <= arr[i].start) {
                Program[] next = copyButExcept(arr, i);
                max = Math.max(max, process(arr[i].end, count + 1, next));
            }
        }
        return max;
    }

    private static Program[] copyButExcept(Program[] arr, int i) {
        Program[] newArr = new Program[arr.length - 1];
        int idx = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != i) {
                newArr[idx++] = arr[j];
            }
        }
        return newArr;
    }

    private static int bestArrange2(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, Comparator.comparingInt(p -> p.end));
        int ans = 0;
        int timeline = 0;
        for (Program p : arr) {
            if (timeline <= p.start) {
                ans++;
                timeline = p.end;
            }
        }
        return ans;
    }

    // for test：随机生成项目
    private static Program[] randomProgram(int maxLen, int maxTime) {
        Program[] arr = new Program[(int) (Math.random() * (maxLen + 1))];
        for (int i = 0; i < arr.length; i++) {
            int start = (int) (Math.random() * (maxTime + 1));
            int end = start + 1 + (int) (Math.random() * (maxTime - start));
            arr[i] = new Program(start, end);
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Program[] arr = randomProgram(5, 100);
            if (bestArrange1(arr) != bestArrange2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Finished!");
    }

    private static void print(Program[] arr) {
        for (Program p : arr) {
            System.out.print("(" + p.start + "," + p.end + "), ");
        }
        System.out.println();
    }
}
