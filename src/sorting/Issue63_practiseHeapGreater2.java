package sorting;

import java.util.*;

/**
 * 加强堆练习题：使用加强堆优化版
 */
public class Issue63_practiseHeapGreater2 {


    public List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        // 得奖区的老板
        HeapGreater<Customer> boss = new HeapGreater<>(new BossComparator());
        // 候选区顾客
        HeapGreater<Customer> cands = new HeapGreater<>(new CandComparator());
        // 顾客
        Map<Integer, Customer> customers = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buy = op[i];
            // 用户购买数为0并且发生退货事件，则判无效
            if (!buy && !customers.containsKey(id)) {
                ans.add(getCurBoss(boss));
            }
            // 三种情况：
            // 用户购买数=0，购买了1件商品
            // 用户购买数>0，购买了1件商品
            // 用户购买数>0，退货了1件商品

            // 新用户
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            // 购买和退货行为
            Customer c = customers.get(id);
            if (buy) {
                c.count++;
            } else {
                c.count--;
            }
            // 如果因为退货行为导致用户购买数为0，则删除该用户
            if (c.count == 0) {
                customers.remove(id);
            }
            // 如果是新用户：既不在得奖区，也不在候选区
            if (!boss.contains(c) && !cands.contains(c)) {
                // 如果得奖区没满，则直接进入得奖区，否则进入候选区
                if (boss.size() < k) {
                    boss.add(c);
                } else {
                    cands.add(c);
                }
            } else if (boss.contains(c)) {
                if (c.count == 0) {
                    boss.remove(c);
                } else {
                    boss.resign(c);
                }
            } else {
                if (c.count == 0) {
                    cands.remove(c);
                } else {
                    cands.resign(c);
                }
            }
            // 重新确定得奖区名单
            refreshBoss(cands, boss, i, k);
            ans.add(getCurBoss(boss));
        }
        return ans;
    }

    public void refreshBoss(HeapGreater<Customer> cands, HeapGreater<Customer> boss, int time, int k) {
        // 候选区没人了跳过
        if (cands.isEmpty()) {
            return;
        }
        // 如果得奖区没满，则候选区排第一的获奖；否则购买数要大于得奖区购买数最少的才能获奖
        if (boss.size() < k) {
            Customer c = cands.poll();
            c.time = time;
            boss.add(c);
        } else {
            if (cands.peek().count > boss.peek().count) {
                Customer oldBoss = boss.poll();
                Customer newBoss = cands.poll();
                oldBoss.time = time;
                newBoss.time = time;
                boss.add(newBoss);
                cands.add(oldBoss);
            }
        }
    }

    public List<Integer> getCurBoss(HeapGreater<Customer> boss) {
        List<Integer> ans = new ArrayList<>();
        List<Customer> list = boss.getAllElements();
        for (Customer c : list) {
            ans.add(c.id);
        }
        return ans;
    }

    // 顾客
    public static class Customer {
        // 顾客编号
        private final int id;
        // 发生事件的时间
        private int time;
        // 购买数
        private int count;

        public Customer(int id, int time, int count) {
            this.id = id;
            this.time = time;
            this.count = count;
        }
    }

    // 得奖区排序规则：购买数升序，如果相等则时间升序 （照顾老人：越早买得越多的用户得奖更多）
    public static class BossComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o1.count - o2.count : o1.time - o2.time;
        }
    }

    // 候选区排序规则：购买数将序，如果相等则时间升序  （给新人机会：买得越多的活跃用户更大概率进入得奖区）
    public static class CandComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o2.count - o1.count : o1.time - o2.time;
        }
    }
}
