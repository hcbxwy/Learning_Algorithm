package graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author hcb
 * @since 2025/9/11 17:26
 */
class Graph {
    HashMap<Integer, Node> nodes;
    HashSet<Edge> edges;

    Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
