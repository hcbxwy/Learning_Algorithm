package tree;

import common.AlgoUtil;
import common.BTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一棵树是否搜索二叉树
 * 定义：任意节点，其左子树最大值小于该节点值，其右子树最小值大于该节点值
 *
 * @author hcb
 * @since 2025/7/29 10:49
 */
public class Issue85_isBST {

    public static void main(String[] args) {
        AlgoUtil.btChecker(Issue85_isBST::isBST, Issue85_isBST::isBST2);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;

        public Info(int mi, int ma, boolean bst) {
            min = mi;
            max = ma;
            isBST = bst;
        }
    }

    // 使用递归套路
    public static boolean isBST(BTNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(BTNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
        }
        int max = x.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max > x.val) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min < x.val) {
            isBST = false;
        }
        return new Info(min, max, isBST);
    }

    // for test：先中序遍历得到一个数组，再判断数组是否升序
    public static boolean isBST2(BTNode head) {
        List<Integer> list = new ArrayList<>();
        inOrder(head, list);
        boolean ans = true;
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(i - 1)) {
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }

    public static void inOrder(BTNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }
}
