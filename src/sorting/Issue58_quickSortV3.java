package sorting;

import other.AlgoUtil;

/**
 * 快排3.0版本
 * 递归数组，每次从子数组的0到arr.length-1中随机取一个值P，实现＜P的放左边，=P的放中间，＞P的放右边
 * <a href="https://leetcode.cn/problems/sort-an-array/">测试链接</a>
 */
public class Issue58_quickSortV3 {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue58_quickSortV3::quickSort);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机确定p
        int p = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] partition = partition(arr, l, r, p);
        process(arr, l, partition[0] - 1);
        process(arr, partition[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l;
        int more = r;
        int index = l;
        while (index <= more) {
            if (arr[index] == p) {
                index++;
            } else if (arr[index] < p) {
                swap(arr, index++, less++);
            } else {
                swap(arr, index, more--);
            }
        }
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
