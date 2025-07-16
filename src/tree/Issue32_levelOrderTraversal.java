package tree;

import common.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 按层遍历二叉树节点
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/">测试链接</a>
 */
public class Issue32_levelOrderTraversal {

    /**
     * 从底向上开始逐层遍历
     */
    public List<List<Integer>> levelOrderBottom(BTNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<BTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                BTNode cur = queue.poll();
                if (cur != null) {
                    curList.add(cur.val);
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            }
            // 每次都在头插入
            ans.add(0, curList);
        }
        return ans;
    }
}
