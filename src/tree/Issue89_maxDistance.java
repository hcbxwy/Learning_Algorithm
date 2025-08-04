package tree;

import common.AlgoUtil;
import common.BTNode;

/**
 * 求二叉树的最大距离
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离， 返回整棵二叉树的最大距离
 *
 * @author hcb
 * @since 2025/8/4 10:11
 */
public class Issue89_maxDistance {

    public static void main(String[] args) {
        BTNode head = AlgoUtil.randomBT();
        System.out.println(maxDistance(head));
        AlgoUtil.printBT(head);
    }

    private static class Info {
        int height;
        int maxDistance;

        private Info(int h, int dis) {
            height = h;
            maxDistance = dis;
        }
    }

    public static int maxDistance(BTNode head) {
        return process(head).maxDistance;
    }

    private static Info process(BTNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info lInfo = process(x.left);
        Info rInfo = process(x.right);
        int height = Math.max(lInfo.height, rInfo.maxDistance) + 1;
        // 可能1：不经过x，最长距离在左边
        int p1 = lInfo.maxDistance;
        // 可能2，不经过x，最长距离在右边
        int p2 = rInfo.maxDistance;
        // 可能3，经过x，则最长距离为 左高+右高+1
        int p3 = lInfo.height + rInfo.height + 1;
        return new Info(height, Math.max(p1, Math.max(p2, p3)));
    }
}
