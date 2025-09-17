package graph;

import java.util.*;

/**
 * 实现拓扑排序
 *
 * @author hcb
 * @since 2025/9/15 16:43
 */
public class Issue106_TopologySort {

    private static List<Node> topSort(Graph graph) {
        // 记录每个节点的剩余入度
        Map<Node, Integer> inMap = new HashMap<>();
        // 入度为0的节点就进入该队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> ans = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            ans.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Edge edge13 = new Edge(0, node1, node3);
        Edge edge23 = new Edge(0, node2, node3);
        Edge edge34 = new Edge(0, node3, node4);
        node1.out = 1;
        node1.nexts.add(node3);

        node2.out = 1;
        node2.nexts.add(node3);

        node3.out = 1;
        node3.in = 2;
        node3.nexts.add(node4);

        node4.in = 1;

        Graph graph = new Graph();
        graph.nodes.put(1, node1);
        graph.nodes.put(2, node2);
        graph.nodes.put(3, node3);
        graph.nodes.put(4, node4);
        graph.edges.add(edge13);
        graph.edges.add(edge23);
        graph.edges.add(edge34);

        List<Node> result = topSort(graph);
        for (Node node : result) {
            System.out.print(node.val + " ");
        }
    }
}
