package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。树的头节点是公司唯一的老板。
 * 除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 *
 * @author hcb
 * @since 2025/8/7 10:22
 */
public class Issue92_maxHappy {

    public static class Employee {
        int happy;
        List<Employee> subs;

        private Employee(int h) {
            happy = h;
            subs = new ArrayList<>();
        }
    }

    private static class Info {
        // 来的最大happy值
        int yes;
        // 不来的最大happy值
        int no;

        private Info(int y, int n) {
            yes = y;
            no = n;
        }
    }

    public static int maxHappy1(Employee head) {
        Info all = process1(head);
        return Math.max(all.yes, all.no);
    }

    private static Info process1(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int yes = x.happy;
        int no = 0;
        for (Employee e : x.subs) {
            Info cur = process1(e);
            yes += cur.no;
            no += Math.max(cur.yes, cur.no);
        }
        return new Info(yes, no);
    }

    public static int maxHappy2(Employee boss) {
        if (boss == null) {
            return 0;
        }
        // 老板没有上级了，所以上级肯定是不参加
        return process2(boss, false);
    }

    /**
     *
     * @param cur 当前员工
     * @param up true表示上级已参加，否则表示未参加
     * @return 最大happy值
     */
    private static int process2(Employee cur, boolean up) {
        if (cur == null) {
            return 0;
        }
        // 如果上级参加了，则自己肯定不能参加了
        if (up) {
            int ans = 0;
            for (Employee n : cur.subs) {
                ans += process2(n, false);
            }
            return ans;
        } else {
            // 如果上级没参加，则自己可参加p1，可不参加p2
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee n : cur.subs) {
                p1 += process2(n, true);
                p2 += process2(n, false);
            }
            return Math.max(p1, p2);
        }
    }

    private static Employee randomBoss(int maxLevel, int maxHappy, int maxSub) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        randomSubs(boss, 1, maxLevel, maxSub, maxHappy);
        return boss;
    }

    private static void randomSubs(Employee cur, int curLevel, int maxLevel, int maxSubs, int maxHappy) {
        if (curLevel > maxLevel) {
            return;
        }
        int subSize = (int) (Math.random() * (maxSubs + 1));
        for (int i = 0; i < subSize; i++) {
            Employee e = new Employee((int) (Math.random() * (maxHappy + 1)));
            cur.subs.add(e);
            randomSubs(e, curLevel + 1, maxLevel, maxSubs, maxHappy);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Employee boos = randomBoss(5, 100, 7);
            if (maxHappy1(boos) != maxHappy2(boos)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Finished!");
    }


}
