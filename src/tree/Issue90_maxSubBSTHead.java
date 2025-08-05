package tree;

import common.AlgoUtil;
import common.BTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树的头节点head， 返回这颗二叉树中最大的二叉搜索子树的头节点
 *
 * @author hcb
 * @since 2025/8/5 09:37
 */
public class Issue90_maxSubBSTHead {

    public static void main(String[] args) {
        AlgoUtil.btNodeChecker(Issue90_maxSubBSTHead::maxSubBSTHead1, Issue90_maxSubBSTHead::maxSubBSTHead);
    }

    /*
        使用容器方案：
        搜索二叉树中序遍历可以得到一个递增数组
        判断每个节点是否二叉树
     */
    public static BTNode maxSubBSTHead1(BTNode head) {
        if (head == null) {
            return null;
        }
        // 是否头
        if (getBSTSize(head) != 0) {
            return head;
        }
        // 不是头，只能是左或右
        BTNode leftAns = maxSubBSTHead1(head.left);
        BTNode rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    // 获取以head为头结点的搜索二叉树节点数，如果不是搜索二叉树则返回0
    private static int getBSTSize(BTNode head) {
        if (head == null) {
            return 0;
        }
        List<BTNode> arr = new ArrayList<>();
        inOrder(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    private static void inOrder(BTNode node, List<BTNode> arr) {
        if (node == null) {
            return;
        }
        inOrder(node.left, arr);
        arr.add(node);
        inOrder(node.right, arr);
    }

    private static class Info {
        int min;
        int max;
        int maxSubBSTSize;
        BTNode maxSubBSTHead;

        private Info(int mi, int ma, int size, BTNode ans) {
            min = mi;
            max = ma;
            maxSubBSTHead = ans;
            maxSubBSTSize = size;
        }
    }

    public static BTNode maxSubBSTHead(BTNode head) {
        Info info = process(head);
        return info == null ? null : info.maxSubBSTHead;
    }

    /*
     * 假设以x为头节点，求其最大搜索子树的头结点：
     * 结果可能性分析：
     * 1、在左子树上
     * 2、在右子树上
     * 3、x就是结果
     *
     */
    private static Info process(BTNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val;
        int max = x.val;
        int maxSubBSTSize = 0;
        BTNode maxSubBSTHead = null;
        // 1、在左子树上
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }
        // 2、在右子树上
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }
        // 3、x就是结果
        if ((leftInfo == null || (leftInfo.maxSubBSTHead == x.left && leftInfo.max < x.val)) &&
                (rightInfo == null || (rightInfo.maxSubBSTHead == x.right && rightInfo.min > x.val))) {
            maxSubBSTHead = x;
            int leftSize = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
            int rightSize = rightInfo == null ? 0 : rightInfo.maxSubBSTSize;
            maxSubBSTSize = leftSize + rightSize + 1;
        }
        return new Info(min, max, maxSubBSTSize, maxSubBSTHead);
    }


}
