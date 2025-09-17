package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用DFS实现拓扑排序
 * 测试地址：https://www.lintcode.com/problem/127/
 * 记录每个节点的深度（从节点出发，能走过的最长路径经过的节点数），根据深度倒序，就是拓扑排序
 *
 * @author hcb
 * @since 2025/9/15 16:43
 */
public class Issue106_TopologySortDFS {

    /**
     * @param graph: A list of Directed graph node
     * @return Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> deepMap = new HashMap<>();
        for (DirectedGraphNode curNode : graph) {
            f(curNode, deepMap);
        }
        List<Record> recordList = new ArrayList<>(deepMap.size());
        deepMap.forEach((node, record) -> recordList.add(record));
        recordList.sort((r1, r2) -> r2.deep - r1.deep);
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : recordList) {
            ans.add(record.node);
        }
        return ans;
    }

    private static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> deepMap) {
        if (deepMap.containsKey(node)) {
            return deepMap.get(node);
        }
        int follow = 0;
        for (DirectedGraphNode next : node.neighbors) {
            follow = Math.max(follow, f(next, deepMap).deep);
        }
        Record record = new Record(node, follow + 1);
        deepMap.put(node, record);
        return record;
    }

    // 计算每个节点的深度
    public static class Record {
        DirectedGraphNode node;
        int deep;

        Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }


    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
}
