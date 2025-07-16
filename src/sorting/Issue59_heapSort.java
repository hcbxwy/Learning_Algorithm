package sorting;

import common.AlgoUtil;

/**
 * 实现堆排序
 */
public class Issue59_heapSort {

    public static void main(String[] args) {
        // int[] nums = new int[]{2, 1, 3, 0, 4};
        // for (int i = 0; i < nums.length; i++) {
        //     heapInsert(nums, i);
        // }
        // AlgoUtil.printArr(nums);
        // nums = new int[]{2, 1, 3, 0, 4};
        // for (int i = nums.length - 1; i >= 0; i--) {
        //     heapify(nums, i);
        // }
        // heapSort(nums);
        // AlgoUtil.printArr(nums);
        AlgoUtil.sortedChecker(Issue59_heapSort::heapSort);
    }

    public static void heapSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int size = nums.length;
        // 方法1：使用堆插入，时间复杂度 O(nlogn)
        // for (int i = 0; i < size; i++) {
        //     heapInsert(nums, i);
        // }
        // 方法2：使用heapify，时间复杂度 O(n)
        for (int i = size - 1; i >= 0; i--) {
            heapify(nums, i, size);
        }
        while (size > 1) {
            swap(nums, 0, --size);
            heapify(nums, 0, size);
        }
    }

    // 晋级赛：跟父比较，能干过就交换
    public static void heapInsert(int[] nums, int index) {
        while (nums[index] > nums[(index - 1) / 2]) {
            swap(nums, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 选拔赛：选较大孩子比较，干不过就交换
    public static void heapify(int[] nums, int index, int size) {
        int l = index * 2 + 1;
        while (l < size) {
            int larger = l + 1 < size && nums[l + 1] > nums[l] ? l + 1 : l;
            int max = nums[index] > nums[larger] ? index : larger;
            if (max == index) {
                break;
            }
            swap(nums, index, max);
            index = max;
            l = index * 2 + 1;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
