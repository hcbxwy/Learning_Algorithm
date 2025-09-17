package graph;

import java.util.*;

/**
 * 用BFS实现拓扑排序
 * 测试地址：https://www.lintcode.com/problem/127/
 *
 * @author hcb
 * @since 2025/9/15 16:43
 */
public class Issue106_TopologySortBFS {

    /**
     * @param graph: A list of Directed graph node
     * @return Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            inMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode next : node.neighbors) {
                inMap.put(next, inMap.get(next) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode node : inMap.keySet()) {
            if (inMap.get(node) == 0) {
                zeroInQueue.offer(node);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode cur = zeroInQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode node : cur.neighbors) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    zeroInQueue.add(node);
                }
            }
        }
        return ans;
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
