package sorting;

import other.AlgoUtil;

import java.util.PriorityQueue;

/**
 * 给一个基本有序的数组排序，基本有序是指数组无序但有个小规律-排序后每个元素移动距离不超过k
 * 例子：
 * 数组arr=[3,2,1,5,4]，k=2
 * 排序后arr=[1,2,3,4,5]，每个元素移动距离不超过2
 */
public class Issue60_heapSort1 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 4};
        int k = 2;
        heapSort(nums, k);
        AlgoUtil.printArr(nums);
    }

    /**
     * 解题思路：
     * 1、0~k用一个小根堆来存
     * 2、小根堆弹出一个，再加入一个
     * 3、没有数加入时，就依次弹出即可
     */
    public static void heapSort(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return;
        }
        // 创建小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i <= k && i < nums.length; i++) {
            minHeap.add(nums[i]);
        }
        // 小根堆弹一个加一个
        int index = 0;
        for (int i = k + 1; i < nums.length; i++) {
            if (!minHeap.isEmpty()) {
                nums[index++] = minHeap.poll();
            }
            minHeap.add(nums[i]);
        }
        // 小根堆没弹完的继续
        while (!minHeap.isEmpty()) {
            nums[index++] = minHeap.poll();
        }

    }
}
