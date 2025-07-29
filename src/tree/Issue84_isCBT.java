package tree;

import common.AlgoUtil;
import common.BTNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是完全二叉树
 * 完全二叉树的概念：
 * 1、任意子树左右高度差不大于1
 * 2、除了最后一层，其他层都是满的，而且最后一层是从左到右依次填满的过程
 *
 * @author hcb
 * @since 2025/7/28 15:26
 */
public class Issue84_isCBT {

    public static void main(String[] args) {
        // BTNode head = AlgoUtil.randomBT();
        // System.out.println(isCBT1(head));
        // AlgoUtil.printBT(head);
        for (int i = 0; i < 10000; i++) {
            BTNode head = AlgoUtil.randomBT();
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops");
            }
        }
        System.out.println("finished");
    }

    // 第一种方案：按层遍历，只要遇到左右不双全的节点，那么该节点后面的节点都是叶节点
    public static boolean isCBT1(BTNode head) {
        if (head == null) {
            return true;
        }
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(head);
        // 是否遇到过左右不双全的节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            BTNode cur = queue.poll();
            // 左空右不空，违背从左到右填满规则
            if (cur.left == null && cur.right != null) {
                return false;
            }
            // 只要遇到左右不双全的节点，那么该节点后面的节点都是叶节点
            if (leaf && cur.left != null) {
                return false;
            }
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (cur.left == null || cur.right == null) {
                leaf = true;
            }
        }
        return true;
    }

    // 第二种方案：用递归套路实现
    public static boolean isCBT2(BTNode head) {
        return process(head).isCBT;
    }

    public static Info process(BTNode x) {
        if (x == null) {
            return new Info(0, true, true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        // 1、左满右满，高度相等
        if (isFull) {
            isCBT = true;
        }
        // 2、左完全右满，左高=右高+1
        else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }
        // 3、左满右满，左高=右高+1
        else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }
        // 4、左满右完全，左高=右高
        else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }
        return new Info(height, isCBT, isFull);
    }

    public static class Info {
        int height;
        boolean isCBT;
        boolean isFull;

        public Info(int h, boolean cbt, boolean full) {
            height = h;
            isCBT = cbt;
            isFull = full;
        }

    }
}
