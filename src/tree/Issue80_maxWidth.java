package tree;

import common.AlgoUtil;
import common.BTNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点
 *
 * @author hcb
 * @since 2025/7/22 10:35
 */
public class Issue80_maxWidth {

    public static void main(String[] args) {
        boolean pass = true;
        BTNode node = null;
        for (int i = 0; i < 10000; i++) {
            BTNode head = AlgoUtil.randomBT();
            if (maxWidth(head) != maxWidth2(head)) {
                pass = false;
                node = head;
                break;
            }
        }
        if (pass) {
            System.out.println("Perfect!");
        } else {
            System.out.println("Oops, something is wrong!");
            AlgoUtil.printBT(node);
        }


    }

    public static int maxWidth(BTNode head) {
        if (head == null) {
            return 0;
        }
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(head);
        BTNode curEndNode = head;
        BTNode nextEndNode = null;
        int max = 0;
        int curSize = 0;
        while (!queue.isEmpty()) {
            BTNode cur = queue.poll();
            curSize++;
            if (cur.left != null) {
                nextEndNode = cur.left;
                queue.add(cur.left);
            }
            if (cur.right != null) {
                nextEndNode = cur.right;
                queue.add(cur.right);
            }
            // 若当前节点是本层最后一个节点，则更新max,curSize归零，curEndNode更新为nextEndNode
            if (cur == curEndNode) {
                max = Math.max(max, curSize);
                curSize = 0;
                curEndNode = nextEndNode;
            }
        }
        return max;
    }

    // for test 使用容器：1️⃣每个节点标记所在层；2️⃣统计每层节点数；3️⃣遍历每层，找出最大值（结果）
    public static int maxWidth2(BTNode head) {
        if (head == null) {
            return 0;
        }
        // value为节点所在层
        Map<BTNode, Integer> map = new HashMap<>();
        map.put(head, 1);
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(head);
        int maxLevel = 1;
        while (!queue.isEmpty()) {
            BTNode cur = queue.poll();
            int curLevel = map.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, curLevel + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, curLevel + 1);
            }
            maxLevel = Math.max(maxLevel, curLevel);
        }
        // 2️⃣统计每层节点数
        int[] levels = new int[maxLevel];
        for (Map.Entry<BTNode, Integer> entry : map.entrySet()) {
            levels[(entry.getValue() - 1)]++;
        }
        // 3️⃣遍历每层，找出最大值（结果）
        int ans = 0;
        for (int val : levels) {
            ans = Math.max(val, ans);
        }
        return ans;
    }
}
