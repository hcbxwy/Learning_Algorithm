package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 收集达标路径和
 * <a href="https://leetcode.com/problems/path-sum-ii/">测试链接</a>
 */
public class Issue36_pathSum2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(2);
        List<List<Integer>> lists = pathSum(root, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(root, path, ans, targetSum, 0);
        return ans;
    }

    public static void process(TreeNode node, List<Integer> path, List<List<Integer>> ans, int targetSum, int preSum) {
        if (node.left == null && node.right == null) {
            if (preSum + node.val == targetSum) {
                path.add(node.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(node.val);
        preSum += node.val;
        if (node.left != null) {
            process(node.left, path, ans, targetSum, preSum);
        }
        if (node.right != null) {
            process(node.right, path, ans, targetSum, preSum);
        }
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> list) {
        List<Integer> newList = new ArrayList<>(list.size());
        newList.addAll(list);
        return newList;
    }
}
