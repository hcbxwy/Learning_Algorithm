package tree;

import common.AlgoUtil;
import common.BTNode;

import java.util.*;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。 返回a和b的最低公共祖先
 *
 * @author hcb
 * @since 2025/8/6 10:40
 */
public class Issue91_lowestAncestor {

    // 方案一：使用容器
    public static BTNode getLowestAncestor1(BTNode head, BTNode a, BTNode b) {
        if (head == null || (a == null && b == null)) {
            return null;
        }
        // 遍历一遍，记录每个节点的父节点
        Map<BTNode, BTNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        getParentMap(head, parentMap);
        Set<BTNode> aParents = new HashSet<>();
        BTNode cur = a;
        aParents.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            aParents.add(cur);
        }
        // 找出a所有祖先
        cur = b;
        while (!aParents.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void getParentMap(BTNode node, Map<BTNode, BTNode> parentMap) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parentMap.put(node.left, node);
            getParentMap(node.left, parentMap);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            getParentMap(node.right, parentMap);
        }
    }

    /*
        方案二：使用二叉树递归套路
        1. 假设节点x是结果，则1️⃣左边找到a，右边找到b；2️⃣左边找到a和b；3️⃣右边找到a和b
        2、假设节点x不是结果，则1️⃣左边无a，右边无a；2️⃣左边无a，右边有a无b；3️⃣左边有a无b
     */
    public static BTNode getLowestAncestor2(BTNode head, BTNode a, BTNode b) {
        return process(head, a, b).ans;
    }

    private static class Info {
        boolean findA;
        boolean findB;
        BTNode ans;

        private Info(boolean a, boolean b, BTNode an) {
            findA = a;
            findB = b;
            ans = an;
        }
    }

    private static Info process(BTNode x, BTNode a, BTNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = x == a || leftInfo.findA || rightInfo.findA;
        boolean findB = x == b || leftInfo.findB || rightInfo.findB;
        BTNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    // for test 随机从一颗二叉树中获取一个节点
    private static BTNode pickRandomOne(BTNode head) {
        if (head == null) {
            return null;
        }
        // 先序遍历存放到一个List中，再从List中随机取
        List<BTNode> preList = new ArrayList<>();
        fillPreList(head, preList);
        int index = (int) (Math.random() * preList.size());
        return preList.get(index);
    }

    // for test
    private static void fillPreList(BTNode head, List<BTNode> preList) {
        if (head == null) {
            return;
        }
        preList.add(head);
        fillPreList(head.left, preList);
        fillPreList(head.right, preList);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            BTNode head = AlgoUtil.randomBT();
            BTNode a = pickRandomOne(head);
            BTNode b = pickRandomOne(head);
            BTNode ans1 = getLowestAncestor1(head, a, b);
            BTNode ans2 = getLowestAncestor2(head, a, b);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println("a: " + (a == null ? null : a.val));
                System.out.println("b: " + (b == null ? null : b.val));
                System.out.println("ans1: " + (ans1 == null ? null : ans1.val));
                System.out.println("ans2: " + (ans2 == null ? null : ans2.val));
                AlgoUtil.printBT(head);
                break;
            }
        }

        System.out.println("Finished!");
    }
}
