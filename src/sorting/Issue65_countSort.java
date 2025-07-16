package sorting;

import common.AlgoUtil;

/**
 * 计数排序
 */
public class Issue65_countSort {

    public static void main(String[] args) {
        AlgoUtil.sortedChecker(Issue65_countSort::countSort);
    }

    private static void countSort(int[] ages) {
        if (ages == null || ages.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int age : ages) {
            max = Math.max(max, age);
        }
        int[] bucket = new int[max + 1];
        for (int age : ages) {
            bucket[age]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                ages[index++] = i;
            }
        }
    }
}
