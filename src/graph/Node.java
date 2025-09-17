package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点
 *
 * @author hcb
 * @since 2025/9/11 17:20
 */
class Node {
    int val;
    int in;
    int out;
    List<Node> nexts;
    List<Edge> edges;

    Node(int value) {
        val = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
